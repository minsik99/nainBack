package io.paioneer.nain.report.jpa.entity;

import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.report.model.dto.RcommunityDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_BOARD_REPORT")
public class RcommunityEntity {
    @Id
    @Column(name="B_REPORT_NO", nullable = false)
    private Long bReportNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MEMEBER_NO", referencedColumnName = "MEMBER_NO")
    private MemberEntity memberEntity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMMENT_NO", referencedColumnName = "COMMENT_NO")
    private CommunityEntity communityEntity;

    @Column(name="REPORT_TYPE", nullable=false)
    private String reportType;

    @Column(name="REPORT_DATE", nullable=false)
    private Date reportDate;

    @Column(name="HANDLED_YN")
    private String handledYN;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADMIN_NO", referencedColumnName = "MEMBER_NO")
    private MemberEntity adminEntity;

    @Column(name="HANDLED_DATE")
    private Date handledDate;

    public RcommunityDto toDto(){
        return RcommunityDto.builder()
                .bReportNo(this.bReportNo)
                .reporter(this.memberEntity.getMemberNickName())
                .communityNo(this.communityEntity.getCommunityNo())
                .reportType(this.reportType)
                .reportDate(this.reportDate)
                .handledYN(this.handledYN)
                .admin(this.adminEntity.getMemberNickName())
                .handledDate(this.handledDate)
                .build();
    }
}
