package io.paioneer.nain.resume.controller;

import io.paioneer.nain.resume.model.dto.EducationDto;
import io.paioneer.nain.resume.model.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;

    // 학력 저장
    @PostMapping("/resume/{resumeNo}/create")
    public ResponseEntity<EducationDto> createEducation(@RequestBody EducationDto educationDto, @PathVariable Long resumeNo) {
        EducationDto createdEducation = educationService.createEducation(educationDto, resumeNo);
        return ResponseEntity.ok(createdEducation);
    }

    // 학력 조회
    @GetMapping("/resume/{resumeNo}")
    public ResponseEntity<List<EducationDto>> getEducationByResumeNo(@PathVariable Long resumeNo) {
        List<EducationDto> education = educationService.getEducationByResumeNo(resumeNo);
        return ResponseEntity.ok(education);
    }

    // 학력 삭제
    @DeleteMapping("/resume/{resumeNo}")
    public ResponseEntity<Void> deleteEducationByResumeNo(@PathVariable Long resumeNo) {
        educationService.deleteEducationByResumeNo(resumeNo);
        return ResponseEntity.noContent().build();
    }

    // 학력 수정
    @PutMapping("/{educationNo}")
    public ResponseEntity<EducationDto> updateEducation(@PathVariable Long educationNo, @RequestBody EducationDto educationDto) {
        EducationDto updatedEducation = educationService.updateEducation(educationNo, educationDto);
        return ResponseEntity.ok(updatedEducation);
    }
}
