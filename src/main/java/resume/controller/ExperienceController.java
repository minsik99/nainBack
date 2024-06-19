package resume.controller;

import io.paioneer.nain.resume.model.dto.ExperienceDto;
import io.paioneer.nain.resume.model.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {
    private final ExperienceService experienceService;

    // 경력 저장
    @PostMapping("/resume/{resumeNo}/create")
    public ResponseEntity<ExperienceDto> createExperience(@RequestBody ExperienceDto experienceDto, @PathVariable Long resumeNo) {
        ExperienceDto createdExperience = experienceService.createExperience(experienceDto, resumeNo);
        return ResponseEntity.ok(createdExperience);
    }

    // 경력 조회
    @GetMapping("/resume/{resumeNo}")
    public ResponseEntity<List<ExperienceDto>> getExperiencesByResumeNo(@PathVariable Long resumeNo) {
        List<ExperienceDto> experiences = experienceService.getExperiencesByResumeNo(resumeNo);
        return ResponseEntity.ok(experiences);
    }

    // 경력 삭제
    @DeleteMapping("/resume/{resumeNo}")
    public ResponseEntity<Void> deleteExperiencesByResumeNo(@PathVariable Long resumeNo) {
        experienceService.deleteExperiencesByResumeNo(resumeNo);
        return ResponseEntity.noContent().build();
    }

    // 경력 업데이트
    @PutMapping("/{experienceNo}")
    public ResponseEntity<ExperienceDto> updateExperience(@PathVariable Long experienceNo, @RequestBody ExperienceDto experienceDto) {
        ExperienceDto updatedExperience = experienceService.updateExperience(experienceNo, experienceDto);
        return ResponseEntity.ok(updatedExperience);
    }
}
