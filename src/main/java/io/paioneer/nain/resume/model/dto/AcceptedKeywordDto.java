package io.paioneer.nain.resume.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.paioneer.nain.resume.jpa.entity.AcceptedKeywordEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AcceptedKeywordDto {
    private Long keywordNo; // 합격 키워드 번호
    private String jobCategory; // 직무 카테고리
    private String acceptKeyword; // 합격 키워드
    private Long frequency; // 사용빈도
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM")
    private Date referenceDate; // 기준 일자

    public AcceptedKeywordEntity toEntity() {
        return AcceptedKeywordEntity.builder()
                .keywordNo(this.keywordNo)
                .jobCategory(this.jobCategory)
                .acceptKeyword(this.acceptKeyword)
                .frequency(this.frequency)
                .referenceDate(this.referenceDate)
                .build();
    }

}
