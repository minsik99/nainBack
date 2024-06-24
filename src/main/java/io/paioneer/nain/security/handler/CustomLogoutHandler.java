package io.paioneer.nain.security.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.model.service.MemberService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import io.paioneer.nain.security.model.entity.RefreshToken;
import io.paioneer.nain.security.service.RefreshService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CustomLogoutHandler implements LogoutHandler {
    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;
    private final MemberService memberService;

    public CustomLogoutHandler(JWTUtil jwtUtil, RefreshService refreshService, MemberService memberService) {
        this.jwtUtil = jwtUtil;
        this.refreshService = refreshService;
        this.memberService = memberService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7); // 'Bearer ' 문자 제거

            try {
                jwtUtil.isTokenExpired(token);
            } catch (ExpiredJwtException e) {
                log.info("Token expired during logout: {}", e.getMessage());
                // HTTP 응답을 설정하여 직접 클라이언트에게 오류 정보를 전달합니다.
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                response.setContentType("application/json");
                try {
                    response.getWriter().write("{\"error\":\"Session has expired. Please log in again.\"}");
                    response.getWriter().flush();
                } catch (IOException ioException) {
                    log.error("Error writing to response", ioException);
                }
                return; // 메서드를 빠져나와 추가 처리를 중단합니다.
            }

            // 만료 여부와 상관없이 사용자 정보를 조회하여 로그아웃 처리를 합니다.
            String userName = jwtUtil.getUserEmailFromToken(token);
            Optional<MemberEntity> userOptional = memberService.findByMemberEmail(userName);
            if (userOptional.isPresent()) {
                MemberEntity member = userOptional.get();

                // 카카오 로그아웃 처리
                if ("kakao".equals(member.getLoginType())) {
                    String kakaoAccessToken = member.getSnsAccessToken(); // 저장된 카카오 액세스 토큰 사용
                    log.info("Kakao access token: {}", kakaoAccessToken);
                    String kakaoLogoutUrl = "https://kapi.kakao.com/v1/user/logout";
                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Authorization", "Bearer " + kakaoAccessToken);

                    HttpEntity<String> kakaoRequestEntity = new HttpEntity<>(headers);
                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<String> kakaoResponse = restTemplate.exchange(kakaoLogoutUrl, HttpMethod.POST, kakaoRequestEntity, String.class);
                        log.info("Kakao logout response = {}", kakaoResponse.getBody());


                }

                List<RefreshToken> refresh = refreshService.findMemberNo(member.getMemberNo());
                if(refresh != null && refresh.size() > 0) {
                    refreshService.deleteByRefresh(refresh.get(0).getTokenValue());
                }

            }
        }

        // 성공적인 로그아웃 응답을 설정합니다.
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
