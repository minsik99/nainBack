package resume.model.service;

import io.paioneer.nain.resume.jpa.entity.ExperienceEntity;
import io.paioneer.nain.resume.jpa.repository.ExperienceRepository;
import io.paioneer.nain.resume.model.dto.ExperienceDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ExperienceService {
    private final ExperienceRepository experienceRepository;

    // 경력 저장
    public ExperienceDto createExperience(ExperienceDto experienceDto, Long resumeNo) {
        ExperienceEntity experienceEntity = experienceDto.toEntity();
        experienceEntity.setResumeNo(resumeNo);
        experienceEntity.setExDuration(calculateDuration(experienceEntity.getStartDate(), experienceEntity.getEndDate()));
        ExperienceEntity savedEntity = experienceRepository.save(experienceEntity);
        return savedEntity.toDto();
    }

    // 근무 기간 계산
    private String calculateDuration(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return "";
        }
        long months = ChronoUnit.MONTHS.between(
                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
        return Long.toString(months);
    }

    // 경력 조회
    public List<ExperienceDto> getExperiencesByResumeNo(Long resumeNo) {
        return experienceRepository.findByResumeNo(resumeNo).stream()
                .map(ExperienceEntity::toDto)
                .collect(Collectors.toList());
    }

    // 경력 업데이트
    public ExperienceDto updateExperience(Long experienceNo, ExperienceDto experienceDto) {
        ExperienceEntity experienceEntity = experienceRepository.findById(experienceNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid experience ID"));

        experienceEntity.setCompany(experienceDto.getCompany());
        experienceEntity.setDepartment(experienceDto.getDepartment());
        experienceEntity.setExPosition(experienceDto.getExPosition());
        experienceEntity.setStartDate(experienceDto.getStartDate());
        experienceEntity.setEndDate(experienceDto.getEndDate());
        experienceEntity.setCurrent(experienceDto.getCurrent());
        experienceEntity.setResponsibilities(experienceDto.getResponsibilities());
        experienceEntity.setExDuration(experienceDto.getExDuration());

        ExperienceEntity updatedExperience = experienceRepository.save(experienceEntity);
        return updatedExperience.toDto();
    }

    // 경력 삭제 (해당 이력서내 경력 전체 삭제)
    public void deleteExperiencesByResumeNo(Long resumeNo) {
        experienceRepository.deleteByResumeNo(resumeNo);
    }
}
