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
public class ReportProcessDto {
    private String reportId;
    private String memberBlock;
    private String communityDelete;
}
