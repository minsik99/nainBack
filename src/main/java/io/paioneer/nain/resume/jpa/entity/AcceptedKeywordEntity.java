package io.paioneer.nain.resume.jpa.entity;

import io.paioneer.nain.resume.model.dto.AcceptedKeywordDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ACCEPTED_KEYWORD")
public class AcceptedKeywordEntity {
    @Id
    @Column(name="KEYWORD_NO", nullable = false)
    private Long keywordNo; // 합격 키워드 번호

    @Column(name="JOB_CATEGORY", nullable = false)
    private String jobCategory; // 직무 카테고리

    @Column(name="ACCEPT_KEYWORD", nullable = false)
    private String acceptKeyword; // 합격 키워드

    @Column(name="FREQUENCY", nullable = false)
    private Long frequency; // 사용빈도

    @Column(name="REFERENCE_DATE", nullable = false)
    private Date referenceDate; // 기준 일자

    public AcceptedKeywordDto toDto() {
        return AcceptedKeywordDto.builder()
                .keywordNo(this.keywordNo)
                .jobCategory(this.jobCategory)
                .acceptKeyword(this.acceptKeyword)
                .frequency(this.frequency)
                .referenceDate(this.referenceDate)
                .build();
    }
}
