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

    private Date itvDate;

    public InterviewEntity toEntity(){
        return InterviewEntity.builder()
                .itvNo(this.itvNo)
                .memberNo(this.memberNo)
                .title(this.title)
                .videoScore(this.videoScore)
                .voiceScore(this.voiceScore)
                .itvDate(this.itvDate)
                .build();
    }
}
