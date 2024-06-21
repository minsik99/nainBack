package io.paioneer.nain.interview.jpa.entity;


import io.paioneer.nain.community.model.dto.CommentDto;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
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
    private Long itvNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_NO")
    private MemberEntity member;


    @Column(name="title", nullable = false)
    private String title;

    @Column(name="VIDEO_SCORE", nullable = false)
    private int videoScore;

    @Column(name="VOICE_SCORE", nullable = false)
    private int voiceScore;

    @Column(name="ITV_DATE", nullable = false)
    private Date itvDate;

    @PrePersist
    protected void onCreate() {
        itvDate = new Date();
    }

    public InterviewDto toDto(){
        return InterviewDto.builder()
                .itvNo(this.itvNo)
                .memberNo(this.member.getMemberNo())
                .title(this.title)
                .videoScore(this.videoScore)
                .voiceScore(this.voiceScore)
                .itvDate(formatDate(this.itvDate)) // 포맷팅된 문자열 사용
                .build();
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(date);
    }
}