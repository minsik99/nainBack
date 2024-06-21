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

    private Long communityNo;
    private Long parentNo;

    private String content;
    private Date commentDate;
    private Date modifiedDate;
    private Date deletedDate;

    public CommentEntity toEntity(){
        return CommentEntity.builder()
                .commentNo(this.commentNo)
                .memberEntity(this.memberDto.toEntity())
                .communityNo(this.communityNo)
                .parentCommentNo(this.parentNo)
                .content(this.content)
                .commentDate(this.commentDate)
                .modifiedDate(this.modifiedDate)
                .deletedDate(this.deletedDate)
                .build();
    }
}
