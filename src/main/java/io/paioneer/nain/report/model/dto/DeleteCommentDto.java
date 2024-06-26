package io.paioneer.nain.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class DeleteCommentDto {
    private Long reportId;
    private Long adminId;
    private Long commentNo;
}
