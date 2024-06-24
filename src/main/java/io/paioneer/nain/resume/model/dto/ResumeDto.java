package io.paioneer.nain.resume.model.dto;

import io.paioneer.nain.resume.jpa.entity.ResumeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Long resumeNo;  // 이력서 번호
    private Long memberNo;  // 회원 번호
    private String title;  // 이력서 제목
    private String resumeName;  // 이름
    private String email;  // 이메일
    private String phone;  // 전화번호
    private String bookMarked;  // 스크랩 여부
    private String jobCategory;  // 직무 카테고리
    private Date createDate;  // 이력서 생성일
    private Date modificationDate;  // 이력서 수정일
    private Date deleteDate;  // 이력서 삭제일
    private String introduction; // 자기 소개서
    private List<ExperienceDto> experiences;  // 경력 리스트
    private List<EducationDto> education;  // 학력 리스트

    public ResumeEntity toEntity() {
        return ResumeEntity.builder()
                .resumeNo(this.resumeNo)
                .memberNo(this.memberNo)
                .title(this.title)
                .resumeName(this.resumeName)
                .email(this.email)
                .phone(this.phone)
                .bookMarked(this.bookMarked)
                .jobCategory(this.jobCategory)
                .createDate(this.createDate)
                .modificationDate(this.modificationDate)
                .deleteDate(this.deleteDate)
                .introduction(this.introduction)
                .build();
    }
}
