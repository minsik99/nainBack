package io.paioneer.nain.interview.model.dto;

import io.paioneer.nain.interview.jpa.entity.QuestionEntity;
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
public class QuestionDto {
    private Long qNo;
    private String qContent;
    private String qType;

    public QuestionEntity toEntity() {
        return QuestionEntity.builder()
                .qNo(this.qNo)
                .qContent(this.qContent)
                .qType(this.qType)
                .build();
    }
}
