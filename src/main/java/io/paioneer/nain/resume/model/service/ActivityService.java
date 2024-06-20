package io.paioneer.nain.resume.model.service;


import io.paioneer.nain.resume.jpa.entity.ActivityEntity;
import io.paioneer.nain.resume.jpa.repository.ActivityRepository;
import io.paioneer.nain.resume.model.dto.ActivityDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    // 활동 저장
    public ActivityDto createActivity(ActivityDto activityDto, Long resumeNo) {
        ActivityEntity activityEntity = activityDto.toEntity();
        activityEntity.setResumeNo(resumeNo);
        ActivityEntity savedEntity = activityRepository.save(activityEntity);
        return savedEntity.toDto();
    }

    // 활동 조회
    public List<ActivityDto> getActivityByResumeNo(Long resumeNo) {
        return activityRepository.findByResumeNo(resumeNo).stream()
                .map(ActivityEntity::toDto)
                .collect(Collectors.toList());
    }

    // 활동 업데이트
    public ActivityDto updateActivity(Long activityNo, ActivityDto activityDto) {
        ActivityEntity activityEntity = activityRepository.findById(activityNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid activity ID"));

        activityEntity.setActivityName(activityDto.getActivityName());
        activityEntity.setActivityDescription(activityDto.getActivityDescription());
        activityEntity.setOrganizer(activityDto.getOrganizer());
        activityEntity.setStartDate(activityDto.getStartDate());
        activityEntity.setEndDate(activityDto.getEndDate());

        ActivityEntity updatedActivity = activityRepository.save(activityEntity);
        return updatedActivity.toDto();
    }

    // 활동 삭제 (해당 이력서내 활동 전체 삭제)
    public void deleteActivitiesByResumeNo(Long resumeNo) {
        activityRepository.deleteByResumeNo(resumeNo);
    }
}
