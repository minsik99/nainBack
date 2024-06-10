package io.paioneer.nain.report.model.dto;

import io.paioneer.nain.community.model.dto.CommentDto;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.report.jpa.entity.RcommentEntity;
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
public class RcommentDto {
    private Long cReportNo;

    private MemberDto memberDto;
    private String reporter;

    private CommentDto commentDto;
    private Long commentNo;

    private String reportType;
    private Date reportDate;
    private String handledYN;

    private MemberEntity adminEntity;
    private String admin;

    private Date handledDate;


    public RcommentEntity toEntity(){
        return RcommentEntity.builder()
                .cReportNo(this.cReportNo)
                .memberEntity(this.memberDto.toEntity())
                .commentEntity(this.commentDto.toEntity())
                .reportType(this.reportType)
                .reportDate(this.reportDate)
                .handledYN(this.handledYN)
                .adminEntity(this.adminEntity)
                .handledDate(this.handledDate)
                .build();
    }

}
