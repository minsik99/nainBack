package io.paioneer.nain.report.model.service;

import io.paioneer.nain.community.jpa.repository.comment.CommentRepository;
import io.paioneer.nain.report.jpa.repository.CommentReportRepository;
import io.paioneer.nain.report.jpa.repository.CommunityReportRepository;
import io.paioneer.nain.report.jpa.repository.RcommentRepository;
import io.paioneer.nain.report.jpa.repository.RcommunityRepository;
import io.paioneer.nain.report.model.dto.CommentReportDto;
import io.paioneer.nain.report.model.dto.CommunityReportDto;
import io.paioneer.nain.report.model.dto.RcommentDto;
import io.paioneer.nain.report.model.dto.RcommunityDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {
    private final RcommunityRepository communityRepository;
    private final RcommentRepository commentRepository;
    private final CommunityReportRepository communityReportRepository;
    private final CommentReportRepository commentReportRepository;

    public void insertBReport(RcommunityDto rcommunityDto) {
        communityRepository.save(rcommunityDto.toEntity());
    }

    public void insertCReport(RcommentDto rcommentDto) {
        commentRepository.save(rcommentDto.toEntity());
    }

    public List<CommunityReportDto> getCommunityReport() {
        return communityReportRepository.getCommunityReport();
    }

    public List<CommentReportDto> getCommentReport() {
        return commentReportRepository.getCommentReport();
    }
}
