package io.paioneer.nain.report.controller;

import io.paioneer.nain.common.TimeFormater;
import io.paioneer.nain.community.model.service.CommentService;
import io.paioneer.nain.community.model.service.CommunityService;
import io.paioneer.nain.member.model.service.MemberService;
import io.paioneer.nain.report.model.dto.*;
import io.paioneer.nain.report.model.service.ReportService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
@CrossOrigin
public class ReportController {
    private final ReportService reportService;
    private final JWTUtil jwtUtil;

    private final MemberService memberService;
    private final CommunityService communityService;
    private final CommentService commentService;

    //글 신고
    @PostMapping("/community")
    public ResponseEntity<Void> reportCommunity(HttpServletRequest request, @RequestBody RcommunityDto rcommunityDto){
        log.info(rcommunityDto.toString());
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        rcommunityDto.setCommunityDto(communityService.getCommunity(rcommunityDto.getCommunityNo()));
        rcommunityDto.setMemberDto(memberService.findById(memberNo));
        rcommunityDto.setReportDate(TimeFormater.TimeCalculate());
        log.info("입력될 신고값 {}",rcommunityDto.toString());
        try{
            reportService.insertBReport(rcommunityDto);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //댓글 신고
    @PostMapping("/comment")
    public ResponseEntity<Void> reportComment(HttpServletRequest request, @RequestBody RcommentDto rcommentDto){
        log.info(rcommentDto.toString());
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);
        rcommentDto.setCommentDto(commentService.getComment(rcommentDto.getCommentNo()));
        rcommentDto.setMemberDto(memberService.findById(memberNo));
        rcommentDto.setReportDate(TimeFormater.TimeCalculate());
        log.info("입력될 신고값 {}",rcommentDto.toString());
        try{
            reportService.insertCReport(rcommentDto);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //게시글 신고 카운트 가져오기
    @GetMapping("/communitycount")
    public ResponseEntity<List<CommunityReportCountDto>> getCommunityReportCount(){
        List<CommunityReportCountDto> communityReportCountDto = reportService.communityReportCount();
        return ResponseEntity.status(HttpStatus.OK).body(communityReportCountDto);
    }

    //게시글 신고리스트 가져오기
    @GetMapping("/community")
    public ResponseEntity<List<CommunityReportDto>> getCommunityReport(){
        List<CommunityReportDto> communityReportDto = reportService.getCommunityReport();
        return ResponseEntity.status(HttpStatus.OK).body(communityReportDto);
    }

    //댓글 신고리스트 가져오기
    @GetMapping("/comment")
    public ResponseEntity<List<CommentReportDto>> getCommentReport(){
        List<CommentReportDto> commentReportDto = reportService.getCommentReport();
        return ResponseEntity.status(HttpStatus.OK).body(commentReportDto);
    }

    @GetMapping("/commentcount")
    public ResponseEntity<List<CommentReportCountDto>> getCommentReportCount(){
        List<CommentReportCountDto> commentReportCountDto = reportService.getCommentReportCountDto();
        return ResponseEntity.status(HttpStatus.OK).body(commentReportCountDto);
    }

    //게시글 삭제처리
    @PostMapping("/community/delete")
    public ResponseEntity<Void> deletePost(@RequestBody DeletePostDto request) {
        reportService.deletePost(request.getReportId(), request.getAdminId(), request.getCommunityNo());
        return ResponseEntity.ok().build();
    }

    //게시글 작성자 계정정지처리
    @PostMapping("/community/blockaccount")
    public ResponseEntity<Void> blockAccountCommunity(@RequestBody BlockAccountDto request) {
        reportService.blockAccountCommunity(
                request.getReportId(),
                request.getAdminId(),
                request.getBlockReason()
        );
        return ResponseEntity.ok().build();
    }

    //댓글 삭제처리
    @PostMapping("/comment/delete")
    public ResponseEntity<Void> deleteComment(@RequestBody DeleteCommentDto request) {
        reportService.deleteComment(request.getReportId(), request.getAdminId(), request.getCommentNo());
        return ResponseEntity.ok().build();
    }

    //댓글 작성자 계정정지처리
    @PostMapping("/comment/blockaccount")
    public ResponseEntity<Void> blockAccountComment(@RequestBody BlockAccountDto request) {
        reportService.blockAccountComment(
                request.getReportId(),
                request.getAdminId(),
                request.getBlockReason()
        );
        return ResponseEntity.ok().build();
    }

}
