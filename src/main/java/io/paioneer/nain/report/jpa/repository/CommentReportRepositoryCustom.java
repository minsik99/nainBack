package io.paioneer.nain.report.jpa.repository;

import io.paioneer.nain.report.model.dto.CommentReportCountDto;
import io.paioneer.nain.report.model.dto.CommentReportDto;

import java.util.List;

public interface CommentReportRepositoryCustom {
    List<CommentReportDto> getCommentReport();

    List<CommentReportCountDto> getCommentReportCount();
}
