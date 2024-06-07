package io.paioneer.nain.report.model.dto;

import io.paioneer.nain.community.model.dto.CommunityDto;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.report.jpa.entity.RcommunityEntity;
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
public class RcommunityDto {
    private Long bReportNo;

    private MemberDto memberDto;
    private Long reporter;

    private CommunityDto communityDto;
    private Long communityNo;

    private String reportType;
    private Date reportDate;
    private String handledYN;

    public RcommunityEntity toEntity(){
        return RcommunityEntity.builder()
                .bReportNo(this.bReportNo)
                .memberEntity(this.memberDto.toEntity())
                .communityEntity(this.communityDto.toEntity())
                .reportType(this.reportType)
                .reportDate(this.reportDate)
                .handledYN(this.handledYN)
                .build();
    }
}
