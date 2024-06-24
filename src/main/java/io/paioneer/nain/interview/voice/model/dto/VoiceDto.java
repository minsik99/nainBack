package io.paioneer.nain.interview.voice.model.dto;

import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.interview.model.dto.QuestionDto;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class VoiceDto {
    private Long voiceNo;

    private InterviewDto interviewDto;
    private Long itvNo;

    private QuestionDto questionDto;
    private String qContent;

    private String voiceContent;

    public VoiceEntity toEntity() throws ParseException {
        return VoiceEntity.builder()
                .voiceNo(this.voiceNo)
                .interviewEntity(this.interviewDto.toEntity())
                .questionEntity(this.questionDto.toEntity())
                .voiceContent(this.voiceContent)
                .build();
    }
}
