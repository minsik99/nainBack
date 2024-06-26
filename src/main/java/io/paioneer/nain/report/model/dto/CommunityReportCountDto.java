package io.paioneer.nain.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class CommunityReportCountDto {
    private Long communityNo;
    private String reportType;
    private Long reportCount;
//    private Long communityNo;
//    private Long reportCount;

}
