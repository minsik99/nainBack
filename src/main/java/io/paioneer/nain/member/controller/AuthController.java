package io.paioneer.nain.member.controller;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.repository.MemberRepository;
import io.paioneer.nain.member.model.output.CustomMemberDetails;
import io.paioneer.nain.member.model.service.MemberService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import io.paioneer.nain.security.model.entity.RefreshToken;
import io.paioneer.nain.security.service.RefreshService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth/kakao")
@Slf4j
public class AuthController {

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${kakao.redirect-signup-uri}")
    private String kakaoRedirectSignupUri;

    @Value("${allowedOrigin}")
    private String allowedOrigin;

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final RefreshService refreshService;
    private final JWTUtil jwtUtil;

    public AuthController(MemberRepository memberRepository, MemberService memberService, RefreshService refreshService, JWTUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.refreshService = refreshService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/kakao/callback")
    public void kakaoLogin(@RequestParam String code, HttpServletResponse response) throws IOException{
        log.info("code={}", code);

        // 엑세스 토큰을 요청하기 귀한 URL 및 헤더 설정
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String tokenRequestBody = "grant_type=authorization_code"
                + "&client_id=" + kakaoClientId
                + "&redirect_uri=" + kakaoRedirectUri
                + "&code=" + code;

        //토큰 요청
        HttpEntity<String> tokenRequestEntity = new HttpEntity<>(tokenRequestBody, tokenHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> tokenResponse = restTemplate.postForEntity(tokenUrl, tokenRequestEntity, String.class);
        log.info("token response = {}", tokenResponse.getBody());

        JSONObject tokenJson = new JSONObject(tokenResponse.getBody());
        String accessToken = tokenJson.getString("access_token");
        log.info("accessToken = {}", accessToken);

        // 사용자 정보를 요청하기 위한 URL 및 헤더 설정
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders userInfoheaders = new HttpHeaders();
        userInfoheaders.set("Authorization", "Bearer " + accessToken);

        // 사용자 정보 요청
        HttpEntity<String> userInfoRequestEntity = new HttpEntity<>(userInfoheaders);
        ResponseEntity<String> userInfoResponse = restTemplate.exchange(userInfoUrl, HttpMethod.GET, userInfoRequestEntity, String.class);
        log.info("user info response = {}", userInfoResponse.getBody());

        JSONObject userJson = new JSONObject(userInfoResponse.getBody());
        String email = userJson.getJSONObject("kakao_account").has("email") ?
                userJson.getJSONObject("kakao_account").getString("email") : "이메일 정보가 없습니다.";
        log.info("email = {}", email);

        Optional<MemberEntity> optionalMember = memberRepository.findByMemberEmailAndLoginType(email, "kakao");

        if(optionalMember.isPresent()){
            MemberEntity memberEntity = optionalMember.get();
            memberEntity.setSnsAccessToken(accessToken);
            memberRepository.save(memberEntity);
            // JWT 토큰 칼급
            Long accessExpiredMs = 360000L;
            String accessTokenJwt = jwtUtil.generateToken(email, "access", accessExpiredMs);
            Long refreshExpiredMs = 86400000L;
            String refreshTokenJwt = jwtUtil.generateToken(email, "refresh", refreshExpiredMs);

            RefreshToken refreshToken = RefreshToken.builder()
                    .id(UUID.randomUUID())
                    .status("activated")
                    .memberAgent(response.getHeader("Member-Agent"))
                    .memberEntity(memberEntity)
                    .tokenValue(refreshTokenJwt)
                    .expiresIn(refreshExpiredMs)
                    .build();

            refreshService.save(refreshToken);

            // 로그인 성공 후 URL에 토큰 정보 포함
            String redirectUrl = String.format(allowedOrigin + "/member/success?access=%s&refresh=%s&admin=%s&memberNo=%d&subscribeYN=%s",
                    accessTokenJwt, refreshTokenJwt, memberEntity.getAdmin(), memberEntity.getMemberNo(), memberEntity.getSubscribeYN());

            response.sendRedirect(redirectUrl);
            log.info("로그인 성공: {}", email);
        } else {
            log.info("회원가입 필요: {}", email);
            response.sendRedirect(allowedOrigin + "/member");
        }
    }

    @GetMapping("/kakao/signup/callback")
    public void kakaoSignup(@RequestParam String code, HttpServletResponse response) throws IOException {
        log.info("code={}", code);

        // 엑세스 토큰을 요청하기 위한 URL 및 헤더 설정
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String tokenRequestBody = "grant_type=authorization_code"
                + "&client_id=" + kakaoClientId
                + "&redirect_uri=" + kakaoRedirectSignupUri
                + "&code=" + code;

        // 토큰 요청
        HttpEntity<String> tokenRequestEntity = new HttpEntity<>(tokenRequestBody, tokenHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> tokenResponse = restTemplate.exchange(tokenUrl, HttpMethod.POST, tokenRequestEntity, String.class);
        log.info("token response = {}", tokenResponse.getBody());

        JSONObject tokenJson = new JSONObject(tokenResponse.getBody());
        String accessToken = tokenJson.getString("access_token");
        log.info("accessToken = {}", accessToken);

        // 사용자 정보를 요청하기 위한 URL 및 헤더 설정
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders userInfoHeaders = new HttpHeaders();
        userInfoHeaders.set("Authorization", "Bearer " + accessToken);

        // 사용자 정보 요청
        HttpEntity<String> userInfoRequestEntity = new HttpEntity<>(userInfoHeaders);
        ResponseEntity<String> userInfoResponse = restTemplate.exchange(userInfoUrl, HttpMethod.GET, userInfoRequestEntity, String.class);
        log.info("user info response = {} ", userInfoResponse.getBody());

        JSONObject userJson = new JSONObject(userInfoResponse.getBody());
        String email = userJson.getJSONObject("kakao_account").has("email") ?
                userJson.getJSONObject("kakao_account").getString("email") : "이메일 정보가 없습니다.";
        log.info("email = {}", email);
        String userName = userJson.getJSONObject("kakao_account").has("name") ?
                userJson.getJSONObject("kakao_account").getString("name") : "유저 정보가 없습니다.";

        Optional<MemberEntity> optionalMember = memberRepository.findByMemberEmailAndLoginType(email, "kakao");

        if(optionalMember.isPresent()){
            log.info("이미 등록된 사용자 : {}", email);
            response.sendRedirect("${allowedOrigin}" + "/member");

        }else {
            MemberEntity newMemberEntity = new MemberEntity();
            newMemberEntity.setMemberEmail(email);
            newMemberEntity.setLoginType("kakao");
            newMemberEntity.setMemberName(userName);
            newMemberEntity.setMemberPwd("");
            newMemberEntity.setAdmin(false);
            newMemberEntity.setSignUpDate(LocalDateTime.now());
            memberRepository.save(newMemberEntity);
            log.info("회원가입 성공: {}", email);

            // 카카오 로그아웃 처리
            String logoutUrl = "https://kapi.kakao.com/v1/user/logout";
            HttpHeaders logoutHeaders = new HttpHeaders();
            logoutHeaders.set("Authorization", "Bearer " + accessToken);

            HttpEntity<String> logoutRequestEntity = new HttpEntity<>(logoutHeaders);
            ResponseEntity<String> logoutResponse = restTemplate.exchange(logoutUrl, HttpMethod.POST, logoutRequestEntity, String.class);
            log.info("logout response = {}", logoutResponse.getBody());

            response.sendRedirect(allowedOrigin + "/member/login");
        }
    }


}




























