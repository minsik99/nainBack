package io.paioneer.nain.notice.jpa.entity;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.notice.model.dto.NoticeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="TB_NOTICE")
@Entity
public class NoticeEntity {

    @Id     //JPA 가 객체를 관리할 때 식별할 기본키 지정하는 어노테이션임
//    @GeneratedValue(strategy = GenerationType.IDENTITY)  //primary key 지정하는 어노테이션
    @Column(name = "NOTICE_NO",nullable = false)
    private Long noticeNo;

    @Column(name = "NOTICE_TITLE", nullable = false, length = 50)
    private String noticeTitle;

    @Column(name = "NOTICE_DATE")
    private Date noticeDate;

    @Column(name = "NOTICE_MODIFY")
    private Date noticeModify;

    @Column(name = "NOTICE_DELETE")
    private Date noticeDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_NO", referencedColumnName = "MEMBER_NO")
    private MemberEntity memberEntity;

    @Column(name = " NOTICE_FILE", nullable = false, length = 100)
    private String noticeFile;

    @Column(name = " NOTICE_MFILE", nullable = false, length = 100)
    private String noticeMFile;

    @Column(name = " NOTICE_CONTENT", nullable = false, length = 1000)
    private String noticeContent;

    @Column(name = "NOTICE_READCOUNT", nullable = false)
    private Long noticeReadCount;

    @Column(name = "NOTICE_IMPORTENT", nullable = false)
    private Date noticeImportent;

    public NoticeDto toDto(){
        return NoticeDto.builder()
                .noticeNo(noticeNo)
                .memberDto(memberEntity.toDto())
                .noticeTitle(noticeTitle)
                .noticeWriter(memberEntity.getMemberNickName())
                .memberNo(memberEntity.toDto().getMemberNo())
                .noticeDate(noticeDate)
                .noticeModify(noticeModify)
                .noticeDelete(noticeDelete)
                .noticeFile(noticeFile)
                .noticeMFile(noticeMFile)
                .noticeContent(noticeContent)
                .noticeReadCount(noticeReadCount)
                .noticeImportent(noticeImportent)
                .build();
    }

}


