package io.paioneer.nain.resume.model.service;

import io.paioneer.nain.resume.jpa.entity.AcceptedKeywordEntity;
import io.paioneer.nain.resume.jpa.repository.AcceptedKeywordRepository;
import io.paioneer.nain.resume.model.dto.AcceptedKeywordDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AcceptedKeywordService {

    @Autowired
    private AcceptedKeywordRepository repository;

    // 직무별 직무 키워드 출력
    public List<AcceptedKeywordDto> getJobKeywordsByCategory(String jobCategory) {
        List<AcceptedKeywordEntity> entities = repository.findTop10JobKeywordsByJobCategory(jobCategory);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // 직무별 요구 경력 출력
    public List<AcceptedKeywordDto> getExpKeywordsByCategory(String jobCategory) {
        List<AcceptedKeywordEntity> entities = repository.findTop10ExpKeywordsByJobCategory(jobCategory);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // 기준 일자 출력
    public Date getLatestReferenceDate(String jobCategory) {
        AcceptedKeywordEntity entity = repository.findFirstByJobCategoryOrderByReferenceDateDesc(jobCategory);
        if (entity != null) {
            return entity.getReferenceDate();
        } else {
            throw new RuntimeException("Reference date not found for job category: " + jobCategory);
        }
    }

    private AcceptedKeywordDto convertToDTO(AcceptedKeywordEntity entity) {
        AcceptedKeywordDto dto = new AcceptedKeywordDto();
        dto.setKeywordNo(entity.getKeywordNo());
        dto.setJobCategory(entity.getJobCategory());
        dto.setAcceptKeyword(entity.getAcceptKeyword());
        dto.setFrequency(entity.getFrequency());
        dto.setReferenceDate(entity.getReferenceDate());
        dto.setKeywordType(entity.getKeywordType());
        return dto;
    }
}
