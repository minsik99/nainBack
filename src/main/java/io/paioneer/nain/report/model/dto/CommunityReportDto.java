package io.paioneer.nain.report.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class CommunityReportDto {
    private Long communityReportId;
    private Date communityReportDate;
    private Long communityNo;
    private String communityReportMemberEmail;
    private String communityReportMemberName;
    private String communityReportHandledYN;
    private String communityTitle;
    private String communityContents;
    private String communityReportType;
    private Date communityReportHandledDate;
    private String communityReportAdminName;
}
