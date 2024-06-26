package io.paioneer.nain.interview.voice.jpa.entity;

import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.voice.model.dto.VoiceDto;
import io.paioneer.nain.interview.voice.model.dto.VoiceSentenceDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_VOICE_SENTENCE")
public class VoiceSentenceEntity {
    @Id
    @Column(name="VS_NO", nullable = false)
    private Long vsNO;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="VOICE_NO", referencedColumnName = "VOICE_NO")
    private VoiceEntity voiceEntity;

    @Column(name="SENTENCE", nullable = false)
    private String sentence;

    @Column(name="VSEN_POSITIVE", nullable = false)
    private float positive;

    @Column(name="VSEN_NEGATIVE", nullable = false)
    private float negative;


    public VoiceSentenceDto toDto(){
        return VoiceSentenceDto.builder()
                .vsNO(this.vsNO)
                .voiceDto(this.voiceEntity.toDto())
                .sentence(this.sentence)
                .positive(this.positive)
                .negative(this.negative)
                .build();
    }
}
