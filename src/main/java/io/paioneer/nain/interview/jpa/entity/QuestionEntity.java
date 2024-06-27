package io.paioneer.nain.interview.jpa.entity;

import io.paioneer.nain.interview.model.dto.QuestionDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_INTERVIEW_QUESTION")
public class QuestionEntity {

    @Id
    @Column(name="Q_NO", nullable = false)
    private Long qNo;

    @Column(name="Q_CONTENT", nullable = false)
    private String qContent;

    @Column(name="Q_TYPE", nullable = false)
    private String qType;

    public QuestionDto toDto(){
        return QuestionDto.builder()
                .qNo(this.qNo)
                .qContent(this.qContent)
                .qType(this.qType)
                .build();
    }
}
