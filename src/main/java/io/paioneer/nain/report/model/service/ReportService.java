package io.paioneer.nain.report.model.service;

import io.paioneer.nain.report.jpa.repository.RcommentRepository;
import io.paioneer.nain.report.jpa.repository.RcommunityRepository;
import io.paioneer.nain.report.model.dto.RcommentDto;
import io.paioneer.nain.report.model.dto.RcommunityDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {
    private final RcommunityRepository communityRepository;
    private final RcommentRepository commentRepository;

    public void insertBReport(RcommunityDto rcommunityDto) {
        communityRepository.save(rcommunityDto.toEntity());
    }

    public void insertCReport(RcommentDto rcommentDto) {
        commentRepository.save(rcommentDto.toEntity());
    }
}
