package io.paioneer.nain.interview.voice.jpa.entity;

import io.paioneer.nain.community.model.dto.CommentDto;
import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.jpa.entity.QuestionEntity;
import io.paioneer.nain.interview.voice.model.dto.VoiceDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
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
@Table(name = "TB_VOICE")
public class VoiceEntity {
    @Id
    @Column(name="VOICE_NO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VOICE_NO")
    @SequenceGenerator(name = "SEQ_VOICE_NO", sequenceName = "SEQ_VOICE_NO", allocationSize = 1)
    private Long voiceNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ITV_NO", referencedColumnName = "ITV_NO")
    private InterviewEntity interviewEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Q_NO", referencedColumnName = "Q_NO")
    private QuestionEntity questionEntity;

    @Column(name="VOICE_CONTENT", nullable = false)
    private String voiceContent;

    public VoiceDto toDto(){
        return VoiceDto.builder()
                .voiceNo(this.voiceNo)
                .interviewDto(this.interviewEntity.toDto())
                .qContent(this.questionEntity.getQContent())
                .voiceContent(this.voiceContent)
                .build();
    }
}
