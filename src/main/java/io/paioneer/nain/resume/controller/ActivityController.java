package io.paioneer.nain.resume.controller;

import io.paioneer.nain.resume.model.dto.ActivityDto;
import io.paioneer.nain.resume.model.service.ActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    // 활동 저장
    @PostMapping("/resume/{resumeNo}/create")
    public ResponseEntity<ActivityDto> createActivity(@RequestBody ActivityDto activityDto, @PathVariable Long resumeNo) {
        ActivityDto createdActivity = activityService.createActivity(activityDto, resumeNo);
        return ResponseEntity.ok(createdActivity);
    }

    // 활동 조회
    @GetMapping("/resume/{resumeNo}")
    public ResponseEntity<List<ActivityDto>> getActivityByResumeNo(@PathVariable Long resumeNo) {
        List<ActivityDto> activities = activityService.getActivityByResumeNo(resumeNo);
        return ResponseEntity.ok(activities);
    }

    // 활동 삭제
    @DeleteMapping("/resume/{resumeNo}")
    public ResponseEntity<Void> deleteActivitiesByResumeNo(@PathVariable Long resumeNo) {
        activityService.deleteActivitiesByResumeNo(resumeNo);
        return ResponseEntity.noContent().build();
    }

    // 활동 수정
    @PutMapping("/{activityNo}")
    public ResponseEntity<ActivityDto> updateActivity(@PathVariable Long activityNo, @RequestBody ActivityDto activityDto) {
        ActivityDto updatedActivity = activityService.updateActivity(activityNo, activityDto);
        return ResponseEntity.ok(updatedActivity);
    }
}
