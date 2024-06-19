package io.paioneer.nain.resume.model.service;

import io.paioneer.nain.resume.jpa.entity.EducationEntity;
import io.paioneer.nain.resume.jpa.repository.EducationRepository;
import io.paioneer.nain.resume.model.dto.EducationDto;
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
public class EducationService {
    private final EducationRepository educationRepository;

    // 학력 저장
    public EducationDto createEducation(EducationDto educationDto, Long resumeNo) {
        EducationEntity educationEntity = educationDto.toEntity();
        educationEntity.setResumeNo(resumeNo);
        EducationEntity savedEntity = educationRepository.save(educationEntity);
        return savedEntity.toDto();
    }

    // 학력 조회
    public List<EducationDto> getEducationByResumeNo(Long resumeNo) {
        return educationRepository.findByResumeNo(resumeNo).stream()
                .map(EducationEntity::toDto)
                .collect(Collectors.toList());
    }

    // 학력 업데이트
    public EducationDto updateEducation(Long educationNo, EducationDto educationDto) {
        EducationEntity educationEntity = educationRepository.findById(educationNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid education ID"));

        educationEntity.setSchoolName(educationDto.getSchoolName());
        educationEntity.setCurrent(educationDto.getCurrent());
        educationEntity.setMajor(educationDto.getMajor());
        educationEntity.setDegree(educationDto.getDegree());
        educationEntity.setScore(educationDto.getScore());
        educationEntity.setStartDate(educationDto.getStartDate());
        educationEntity.setEndDate(educationDto.getEndDate());

        EducationEntity updatedEducation = educationRepository.save(educationEntity);
        return updatedEducation.toDto();
    }

    // 학력 삭제 (해당 이력서내 교육 전체 삭제)
    public void deleteEducationByResumeNo(Long resumeNo) {
        educationRepository.deleteByResumeNo(resumeNo);
    }
}

