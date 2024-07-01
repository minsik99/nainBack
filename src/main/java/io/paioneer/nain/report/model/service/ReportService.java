package io.paioneer.nain.report.model.service;

import io.paioneer.nain.blockMember.entity.BlockMemberEntity;
import io.paioneer.nain.blockMember.jpa.BlockMemberRepository;
import io.paioneer.nain.community.jpa.entity.CommentEntity;
import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import io.paioneer.nain.community.jpa.repository.comment.CommentRepository;
import io.paioneer.nain.community.jpa.repository.community.CommunityRepository;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.repository.MemberRepository;
import io.paioneer.nain.report.jpa.entity.RcommentEntity;
import io.paioneer.nain.report.jpa.entity.RcommunityEntity;
import io.paioneer.nain.report.jpa.repository.CommentReportRepository;
import io.paioneer.nain.report.jpa.repository.CommunityReportRepository;
import io.paioneer.nain.report.jpa.repository.RcommentRepository;
import io.paioneer.nain.report.jpa.repository.RcommunityRepository;
import io.paioneer.nain.report.model.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {
    private final RcommunityRepository rcommunityRepository;
    private final RcommentRepository rcommentRepository;
    private final CommunityReportRepository communityReportRepository;
    private final CommentReportRepository commentReportRepository;
    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;
    private final CommentRepository commentRepository;
    private final BlockMemberRepository blockMemberRepository;

    public void insertBReport(RcommunityDto rcommunityDto) {
        rcommunityRepository.save(rcommunityDto.toEntity());
    }

    public void insertCReport(RcommentDto rcommentDto) {
        rcommentRepository.save(rcommentDto.toEntity());
    }

    public List<CommunityReportCountDto> communityReportCount() {
        return communityReportRepository.getCommunityReportCount();
    }

    public List<CommunityReportDto> getCommunityReport() {
        List<CommunityReportDto> allReports = communityReportRepository.getCommunityReport();
        Map<Long, CommunityReportDto> uniqueReports = new LinkedHashMap<>();

        for (CommunityReportDto report : allReports) {
            Long communityNo = report.getCommunityNo(); // communityNo 기준으로 중복 제거
            if (!uniqueReports.containsKey(communityNo) ||
                    report.getCommunityReportDate().after(uniqueReports.get(communityNo).getCommunityReportDate())) {
                uniqueReports.put(communityNo, report);
            }
        }

        return new ArrayList<>(uniqueReports.values());
    }

    public List<CommentReportDto> getCommentReport() {
        List<CommentReportDto> allReports = commentReportRepository.getCommentReport();
        Map<Long, CommentReportDto> uniqueReports = new LinkedHashMap<>();
        for (CommentReportDto report : allReports) {
            Long commentNo = report.getCommentNo();
            if (!uniqueReports.containsKey(commentNo) ||
                    report.getCommentReportDate().after(uniqueReports.get(commentNo).getCommentReportDate())){
                uniqueReports.put(commentNo, report);
            }
        }
        return new ArrayList<>(uniqueReports.values());
    }

    public List<CommentReportCountDto> getCommentReportCountDto() {
        log.info("getCommentReportCountDto");
        return commentReportRepository.getCommentReportCount();
    }

    public void deletePost(Long reportId, Long adminId, Long communityNo) {
        RcommunityEntity rcommunityEntity = rcommunityRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        MemberEntity memberEntity = memberRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        CommunityEntity communityEntity = communityRepository.findById(communityNo)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        rcommunityEntity.setHandledYN("Y");
        rcommunityEntity.setAdminEntity(memberEntity); // adminEntity를 memberEntity로 설정
        rcommunityEntity.setHandledDate(new Date()); // 처리 날짜 설정
        communityReportRepository.save(rcommunityEntity);

        communityEntity.setDeletedDate(new Date()); // 삭제 날짜 설정
        communityRepository.save(communityEntity);
    }


    public void blockAccountCommunity(Long reportId, Long adminId, String blockReason) {
        log.info("blockAccountCommunity 시작 - reportId: {}, adminId: {}, blockReason: {}", reportId, adminId, blockReason);

        try {
            RcommunityEntity rcommunityEntity = rcommunityRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Report not found"));
            log.info("rcommunityEntity 조회 성공 - reportId: {}", reportId);

            MemberEntity adminEntity = memberRepository.findById(adminId)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            log.info("adminEntity 조회 성공 - adminId: {}", adminId);

            rcommunityEntity.setHandledYN("Y");
            rcommunityEntity.setAdminEntity(adminEntity);
            rcommunityEntity.setHandledDate(new Date());
            communityReportRepository.save(rcommunityEntity);
            log.info("rcommunityEntity 저장 성공");

            BlockMemberEntity blockMemberEntity = new BlockMemberEntity();
            blockMemberEntity.setMemberNo2(rcommunityEntity.getMemberEntity());
            blockMemberEntity.setBlockYN("Y");
            blockMemberEntity.setBlockComment(blockReason);
            blockMemberEntity.setBlockDate(new Date());
            log.info("blockMemberEntity 생성 완료");

            blockMemberRepository.save(blockMemberEntity);
            log.info("blockMemberEntity 저장 성공");
        } catch (Exception e) {
            log.error("blockAccountCommunity 처리 중 오류 발생", e);
            throw e;
        }
    }

    public void deleteComment(Long reportId, Long adminId, Long commentNo) {
        RcommentEntity rcommentEntity = rcommentRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        MemberEntity memberEntity = memberRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        CommentEntity commentEntity = commentRepository.findById(commentNo)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        rcommentEntity.setHandledYN("Y");
        rcommentEntity.setAdminEntity(memberEntity); // adminEntity를 memberEntity로 설정
        rcommentEntity.setHandledDate(new Date()); // 처리 날짜 설정
        commentReportRepository.save(rcommentEntity);

        commentEntity.setDeletedDate(new Date()); // 삭제 날짜 설정
        commentRepository.save(commentEntity);
    }

    public void blockAccountComment(Long reportId, Long adminId, String blockReason) {
        RcommentEntity rcommentEntity = rcommentRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        MemberEntity adminEntity = memberRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        rcommentEntity.setHandledYN("Y");
        rcommentEntity.setAdminEntity(adminEntity); // adminEntity를 memberEntity로 설정
        rcommentEntity.setHandledDate(new Date()); // 처리 날짜 설정
        commentReportRepository.save(rcommentEntity);

        BlockMemberEntity blockMemberEntity = new BlockMemberEntity();
        blockMemberEntity.setMemberNo2(rcommentEntity.getMemberEntity()); // memberEntity 설정
        blockMemberEntity.setBlockYN("Y");
        blockMemberEntity.setBlockComment(blockReason);
        blockMemberEntity.setBlockDate(new Date());

        blockMemberRepository.save(blockMemberEntity);
    }

    public boolean alreadyReported(Long memberNo, Long communityNo) {
        boolean history = false;
        int count = rcommunityRepository.reportHistoryCount(memberNo, communityNo);
        if(count > 0){
            history = true;
        }
        log.info("history t/f {}, {}", count, history);
        return history;
    }

    public boolean alreadyReportedComment(Long memberNo, Long commentNo) {
        boolean history = false;
        int count = rcommentRepository.reportCommentHistoryCount(memberNo, commentNo);
        if(count > 0){
            history = true;
        }
        log.info("history t/f {}, {}", count, history);
        return history;
    }
}
