package io.paioneer.nain.community.jpa.entity;


import io.paioneer.nain.community.model.dto.CommunityDto;
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
@Table(name = "TB_COMMUNITY_BOARD")
public class CommunityEntity {
    @Id
    @Column(name="COMMUNITY_NO", nullable = false)
    private Long communityNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_NO", referencedColumnName = "MEMBER_NO")
    private MemberEntity memberEntity;

    @Column(name="TITLE", nullable = false)
    private String title;

    @Column(name="COMMUNITY_CONTENT", nullable = false)
    private String content;

    @Column(name="FILE_UPLOAD", nullable = false)
    private String fileUpload;

    @Column(name="FILE_MODIFIED", nullable = false)
    private String fileModified;

    @Column(name="COMMUNITY_DATE")
    private Date communityDate;

    @Column(name="MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name="DELETED_DATE")
    private Date deletedDate;

    @Column(name="READCOUNT", nullable = false)
    private Long readCount;




    public CommunityDto toDto(){

        return CommunityDto.builder()
                .communityNo(this.communityNo)
                .memberDto(this.memberEntity.toDto())
                .writer(this.memberEntity.getMemberNickName())
                .title(this.title)
                .content(this.content)
                .fileUpload(this.fileUpload)
                .fileModified(this.fileModified)
                .communityDate(this.communityDate)
                .modifiedDate(this.modifiedDate)
                .deletedDate(this.deletedDate)
                .readCount(this.readCount)
                .build();
    }
}
