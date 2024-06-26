package io.paioneer.nain.report.model.service;

import io.paioneer.nain.blockMember.entity.BlockMemberEntity;
import io.paioneer.nain.blockMember.jpa.BlockMemberRepository;
import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import io.paioneer.nain.community.jpa.repository.community.CommunityRepository;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import io.paioneer.nain.member.jpa.repository.MemberRepository;
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
    private final RcommunityRepository RcommunityRepository;
    private final RcommentRepository RcommentRepository;
    private final CommunityReportRepository communityReportRepository;
    private final CommentReportRepository commentReportRepository;
    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;
    private final BlockMemberRepository blockMemberRepository;

    public void insertBReport(RcommunityDto rcommunityDto) {
        RcommunityRepository.save(rcommunityDto.toEntity());
    }

    public void insertCReport(RcommentDto rcommentDto) {
        RcommentRepository.save(rcommentDto.toEntity());
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
        return commentReportRepository.getCommentReport();
    }

    public void deletePost(Long reportId, Long adminId, Long communityNo) {
        RcommunityEntity rcommunityEntity = RcommunityRepository.findById(reportId)
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

    public void blockAccount(Long reportId, Long adminId, String blockReason) {
        RcommunityEntity rcommunityEntity = RcommunityRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        MemberEntity adminEntity = memberRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        rcommunityEntity.setHandledYN("Y");
        rcommunityEntity.setAdminEntity(adminEntity); // adminEntity를 memberEntity로 설정
        rcommunityEntity.setHandledDate(new Date()); // 처리 날짜 설정
        communityReportRepository.save(rcommunityEntity);

        BlockMemberEntity blockMemberEntity = new BlockMemberEntity();
        blockMemberEntity.setMemberNo2(rcommunityEntity.getMemberEntity()); // memberEntity 설정
        blockMemberEntity.setBlockYN("Y");
        blockMemberEntity.setBlockComment(blockReason);
        blockMemberEntity.setBlockDate(new Date());
        blockMemberRepository.save(blockMemberEntity);
    }
}
