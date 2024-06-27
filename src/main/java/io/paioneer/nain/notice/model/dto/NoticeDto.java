package io.paioneer.nain.notice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
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
public class NoticeDto {
    private Long noticeNo;
    private String noticeTitle;
    private String noticeWriter;
    private Date noticeDate;
    private Date noticeModify;
    private Date noticeDelete;
    private MemberDto memberDto;
    private String noticeFile;
    private String noticeMfile;
    private String noticeContent;
    private Long noticeReadCount;
    private Date noticeImportent;

    public NoticeEntity toEntity(){
        return NoticeEntity.builder()
                .noticeNo(noticeNo)
                .noticeTitle(noticeTitle)
                .noticeDate(noticeDate)
                .noticeModify(noticeModify)
                .noticeDelete(noticeDelete)
                .memberEntity(memberDto.toEntity())
                .noticeFile(noticeFile)
                .noticeMfile(noticeMfile)
                .noticeContent(noticeContent)
                .noticeReadCount(noticeReadCount)
                .noticeImportent(noticeImportent)
                .build();
    }


}
