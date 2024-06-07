package io.paioneer.nain.community.model.dto;

import io.paioneer.nain.community.jpa.entity.CommentEntity;
import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import io.paioneer.nain.member.model.dto.MemberDto;
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
public class CommentDto {
    private Long commentNo;

    private MemberDto memberDto;
    private String writer;

    private CommunityDto communityDto;
    private Long communityNo;

    private CommentDto commentDto;
    private Long parentNo;

    private String content;
    private Date commentDate;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
                .commentNo(this.commentNo)
                .memberEntity(this.memberDto.toEntity())
                .communityEntity(this.communityDto.toEntity())
                .commentEntity(this.commentDto.toEntity())
                .content(this.content)
                .commentDate(this.commentDate)
                .build();
    }
}
