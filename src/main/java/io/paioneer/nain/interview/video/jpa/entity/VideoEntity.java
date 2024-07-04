package io.paioneer.nain.interview.video.jpa.entity;


import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TB_VIDEO")
public class VideoEntity {
    @Id
    @Column(name="VIDEO_NO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VIDEO_NO")
    @SequenceGenerator(name = "SEQ_VIDEO_NO", sequenceName = "SEQ_VIDEO_NO", allocationSize = 1)
    private Long videoNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ITV_NO")
    private InterviewEntity interviewEntity;

    @Column(name="ANSWER_NO", nullable = false)
    private Integer answerNo;

    @Column(name="RESULT_DATA", nullable = false)
    private double videoScore;

    @Column(name="ITV_TYPE", nullable = false)
    private String itvType;




}