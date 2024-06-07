package io.paioneer.nain.community.model.dto;

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
public class CommunityDto {
    private Long communityNo;
    private MemberDto memberDto;
    private String writer;
    private String title;
    private String content;
    private String fileUpload;
    private String fileModified;
    private Date communityDate;
    private String readCount;

    public CommunityEntity toEntity(){
        return CommunityEntity.builder()
                .communityNo(this.communityNo)
                .memberEntity(this.memberDto.toEntity())
                .title(this.title)
                .content(this.content)
                .fileUpload(this.fileUpload)
                .fileModified(this.fileModified)
                .communityDate(this.communityDate)
                .readCount(this.readCount)
                .build();
    }
}
