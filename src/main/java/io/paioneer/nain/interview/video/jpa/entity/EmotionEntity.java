package io.paioneer.nain.interview.video.jpa.entity;
import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="TB_EMOTION")
public class EmotionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMOTION_NO")
    @SequenceGenerator(name = "SEQ_EMOTION_NO", sequenceName = "SEQ_EMOTION_NO", allocationSize = 1)
    @Column(name = "EMOTION_NO")
    private Long emotionNo; // 비디오 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ITV_NO")
    private InterviewEntity interviewEntity;

    @Column(name = "ANSWER_ENO")
    private Integer answerEno; // 답변 순서

    @Column(name = "ERESULT_DATA")
    private Double eresultData; // 결과 수치

    @Column(name = "EMOTION_NAME")
    private String emotionName; // 감정명
}


