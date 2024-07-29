package io.paioneer.nain.interview.jpa.entity;


import io.paioneer.nain.common.TimeFormater;
import io.paioneer.nain.community.model.dto.CommentDto;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import java.time.LocalDateTime;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TB_INTERVIEW")
public class InterviewEntity {
    @Id
    @Column(name="ITV_NO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITV_NO")
    @SequenceGenerator(name = "SEQ_ITV_NO", sequenceName = "SEQ_ITV_NO", allocationSize = 1)
    private Long itvNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_NO")
    private MemberEntity memberEntity;


    @Column(name="title", nullable = false)
    private String title;


    @Column(name="VIDEO_SCORE", nullable = false)
    private double videoScore;

    @Column(name="VOICE_SCORE", nullable = false)
    private double voiceScore;

    @Column(name="ITV_DATE", nullable = false)
    private Date itvDate;

    @PrePersist
    protected void onCreate() {
        this.videoScore = 40.0;
        this.voiceScore = 40.0;
        this.itvDate = TimeFormater.TimeCalculate();
    }

    public InterviewDto toDto(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String itvDateInfo = dateFormat.format(this.itvDate);
        return InterviewDto.builder()
                .itvNo(this.itvNo)
                .memberDto(this.memberEntity != null ? this.memberEntity.toDto() : null)
                .memberNo(this.memberEntity != null ? this.memberEntity.getMemberNo() : null)
                .title(this.title)
                .videoScore(this.videoScore)
                .voiceScore(this.voiceScore)
                .itvDateInfo(itvDateInfo) // 포맷팅된 문자열 사용
                .build();
    }

}
