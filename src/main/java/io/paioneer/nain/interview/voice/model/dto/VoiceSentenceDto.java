package io.paioneer.nain.interview.voice.model.dto;

import io.paioneer.nain.interview.voice.jpa.entity.VoiceEntity;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;
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
public class VoiceSentenceDto {

    private Long vsNO;
    private VoiceDto voiceDto;
    private String sentence;
    private float positive;
    private float negative;

    public VoiceSentenceEntity toEntity() throws ParseException {
        return VoiceSentenceEntity.builder()
                .vsNO(this.vsNO)
                .voiceEntity(this.voiceDto.toEntity())
                .sentence(this.sentence)
                .positive(this.positive)
                .negative(this.negative)
                .build();
    }
}
