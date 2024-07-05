package io.paioneer.nain.resume.controller;

import io.paioneer.nain.resume.model.dto.ResumeDto;
import io.paioneer.nain.resume.model.service.ResumeService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final JWTUtil jwtUtil;

    // 내 이력서 조회 (list)
    @GetMapping("/member")
    public ResponseEntity<List<ResumeDto>> getResumesByMemberNo(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);
        List<ResumeDto> resumes = resumeService.findResumesByMemberNo(memberNo);
        return ResponseEntity.ok(resumes);
    }

    // 이력서 작성
    @PostMapping("/create")
    public ResponseEntity<ResumeDto> createResume(HttpServletRequest request,
                                                  @RequestBody ResumeDto resumeDto) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        token = token.substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);
        log.info("resumeDto: {}", resumeDto);

        // memberNo 설정
        resumeDto.setMemberNo(memberNo);

        ResumeDto createdResume = resumeService.createResume(resumeDto);
        return ResponseEntity.ok(createdResume);
    }

    // 이력서 삭제
    @DeleteMapping("/{resumeNo}")
    public ResponseEntity<Void> deleteResume(HttpServletRequest request, @PathVariable Long resumeNo) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        token = token.substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);
        log.info("In resumeController : Deleting resume with resumeNo: {}", resumeNo);

        // 이력서가 로그인한 사용자에 속하는지 확인
        ResumeDto resume = resumeService.findResumeByNo(resumeNo);
        if (resume == null || !resume.getMemberNo().equals(memberNo)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        resumeService.deleteResume(resumeNo);
        return ResponseEntity.noContent().build();
    }

    // 이력서 상세 조회 (1개)
    @GetMapping("/{resumeNo}")
    public ResponseEntity<ResumeDto> getResumeByNo(@PathVariable Long resumeNo) {
        ResumeDto resume = resumeService.findResumeByNo(resumeNo);
        return ResponseEntity.ok(resume);
    }

    // 이력서 수정
    @PutMapping("/{resumeNo}")
    public ResponseEntity<ResumeDto> updateResume(HttpServletRequest request, @PathVariable Long resumeNo, @RequestBody ResumeDto resumeDto) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        token = token.substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);

        // 이력서가 로그인한 사용자에 속하는지 확인
        ResumeDto existingResume = resumeService.findResumeByNo(resumeNo);
        if (existingResume == null || !existingResume.getMemberNo().equals(memberNo)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        log.info("In resumeController : Updating resume with resumeNo: {}", resumeNo);
        ResumeDto updatedResume = resumeService.updateResume(resumeNo, resumeDto);
        return ResponseEntity.ok(updatedResume);
    }

}
