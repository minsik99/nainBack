package io.paioneer.nain.interview.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_INTERVIEW_ANALYSIS")
@Data
public class AnalysisEntity {
    @Id
    @Column(name="ANALYSIS_NO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ANALYSIS_NO")
    @SequenceGenerator(name = "SEQ_ANALYSIS_NO", sequenceName = "SEQ_ANALYSIS_NO", allocationSize = 1)
    private Long analysisNo;

    @Column(name="ANALYSIS_CONTENT")
    private String analysisContent;
    @Column(name="ANALYSIS_TYPE")
    private String analysisType;
    @Column(name = "ANALYSIS_CATEGORY")
    private String analysisCategory;
}
