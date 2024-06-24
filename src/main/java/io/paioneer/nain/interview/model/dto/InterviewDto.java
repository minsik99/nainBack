package io.paioneer.nain.interview.model.dto;

import io.paioneer.nain.community.jpa.entity.CommentEntity;
import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class InterviewDto {
    private Long itvNo;

    private Long memberNo;

    private String title;

    private int videoScore;

    private int voiceScore;

    private String itvDate; // Date 대신 String 타입으로 변경

    public InterviewDto(InterviewEntity entity) {
        this.itvNo = entity.getItvNo();
        this.memberNo = entity.getMember().getMemberNo();
        this.title = entity.getTitle();
        this.videoScore = entity.getVideoScore();
        this.voiceScore = entity.getVoiceScore();
        this.itvDate = formatDate(entity.getItvDate());
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(date);
    }

}
