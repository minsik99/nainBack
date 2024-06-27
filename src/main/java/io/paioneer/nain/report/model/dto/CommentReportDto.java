package io.paioneer.nain.report.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class CommentReportDto {
    private Long commentReportId;
    private Date commentReportDate;
    private Long commentNo;
    private String commentReportMemberEmail;
    private String commentReportMemberName;
    private String commentReportHandledYN;
    private String commentContents;
    private String commentReportType;
    private Date commentReportHandledDate;
    private String commentReportAdminName;
}

