package io.paioneer.nain.interview.model.dto;

import io.paioneer.nain.community.jpa.entity.CommentEntity;
import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.model.dto.MemberDto;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class InterviewDto {
    private Long itvNo;

    private MemberDto memberDto;
    private Long memberNo;

    private String title;

    private int videoScore;

    private int voiceScore;

    private Date itvDate; // Date 대신 String 타입으로 변경

    public InterviewEntity toEntity(){
        return InterviewEntity.builder()
                .itvNo(this.itvNo)
                .memberEntity(this.memberDto.toEntity())
                .title(this.title)
                .videoScore(this.videoScore)
                .voiceScore(this.voiceScore)
                .itvDate(this.itvDate)
                .build();
    }
}
