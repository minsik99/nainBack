package io.paioneer.nain.resume.model.service;

import io.paioneer.nain.resume.jpa.entity.AcceptedKeywordEntity;
import io.paioneer.nain.resume.jpa.repository.AcceptedKeywordRepository;
import io.paioneer.nain.resume.model.dto.AcceptedKeywordDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AcceptedKeywordService {

    @Autowired
    private AcceptedKeywordRepository repository;

    public List<AcceptedKeywordDto> getKeywordsByCategory(String jobCategory) {
        List<AcceptedKeywordEntity> entities = repository.findByJobCategory(jobCategory);
        return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AcceptedKeywordDto convertToDTO(AcceptedKeywordEntity entity) {
        AcceptedKeywordDto dto = new AcceptedKeywordDto();
        dto.setKeywordNo(entity.getKeywordNo());
        dto.setJobCategory(entity.getJobCategory());
        dto.setAcceptKeyword(entity.getAcceptKeyword());
        dto.setFrequency(entity.getFrequency());
        dto.setReferenceDate(entity.getReferenceDate());
        return dto;
    }

}
