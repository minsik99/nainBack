package resume.controller;

import io.paioneer.nain.resume.model.dto.ResumeDto;
import io.paioneer.nain.resume.model.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/member/{memberNo}")
    public ResponseEntity<List<ResumeDto>> getResumesByMemberNo(@PathVariable Long memberNo) {
        List<ResumeDto> resumes = resumeService.findResumesByMemberNo(memberNo);
        return ResponseEntity.ok(resumes);
    }

    @PostMapping("/create")
    public ResponseEntity<ResumeDto> createResume(@RequestBody ResumeDto resumeDto) {
        resumeDto.setMemberNo(1L);  // 임시로 memberNo 값을 1로 설정
        ResumeDto createdResume = resumeService.createResume(resumeDto);
        return ResponseEntity.ok(createdResume);
    }

    @DeleteMapping("/{resumeNo}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long resumeNo) {
        log.info("In resumeController : Deleting resume with resumeNo: {}", resumeNo);
        resumeService.deleteResume(resumeNo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{resumeNo}")
    public ResponseEntity<ResumeDto> getResumeByNo(@PathVariable Long resumeNo) {
        ResumeDto resume = resumeService.findResumeByNo(resumeNo);
        return ResponseEntity.ok(resume);
    }

    @PutMapping("/{resumeNo}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Long resumeNo, @RequestBody ResumeDto resumeDto) {
        ResumeDto updatedResume = resumeService.updateResume(resumeNo, resumeDto);
        return ResponseEntity.ok(updatedResume);
    }

}
