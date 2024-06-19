package resume.model.service;

import io.paioneer.nain.resume.jpa.entity.ResumeEntity;
import io.paioneer.nain.resume.jpa.repository.ResumeRepository;
import io.paioneer.nain.resume.model.dto.ResumeDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public List<ResumeDto> findResumesByMemberNo(Long memberNo) {
        return resumeRepository.findByMemberNo(memberNo).stream()
                .map(ResumeEntity::toDto)
                .collect(Collectors.toList());
    }

    public ResumeDto createResume(ResumeDto resumeDto) {
        resumeDto.setMemberNo(1L);  // 임시로 memberNo 값을 1로 설정
        ResumeEntity resumeEntity = resumeDto.toEntity();
        ResumeEntity savedResume = resumeRepository.save(resumeEntity);
        return savedResume.toDto();
    }

    // 이력서 삭제
    public void deleteResume(Long resumeNo) {
        log.info("In resumeService : Deleting resume with resumeNo: {}", resumeNo);
        resumeRepository.deleteById(resumeNo);
    }

    // 이력서 찾기
    public ResumeDto findResumeByNo(Long resumeNo) {
        ResumeEntity resumeEntity = resumeRepository.findById(resumeNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resume ID"));
        return resumeEntity.toDto();
    }

    // 이력서 업데이트
    public ResumeDto updateResume(Long resumeNo, ResumeDto resumeDto) {
        ResumeEntity resumeEntity = resumeRepository.findById(resumeNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resume ID"));

        resumeEntity.setTitle(resumeDto.getTitle());
        resumeEntity.setResumeName(resumeDto.getResumeName());
        resumeEntity.setEmail(resumeDto.getEmail());
        resumeEntity.setPhone(resumeDto.getPhone());
        resumeEntity.setIntroduction(resumeDto.getIntroduction());
        resumeEntity.setJobCategory(resumeDto.getJobCategory());
        resumeEntity.setModificationDate(new Date());

        ResumeEntity updatedResume = resumeRepository.save(resumeEntity);
        return updatedResume.toDto();
    }
}