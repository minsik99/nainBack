package io.paioneer.nain.report.controller;

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

    //글 신고
    @PostMapping("/community")
    public ResponseEntity<Void> reportCommunity(HttpServletRequest request, @RequestBody RcommunityDto rcommunityDto){
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        rcommunityDto.setReporter(memberService.findById(memberNo).getMemberNickName());
        rcommunityDto.setReportDate(new Date());
        try{
            reportService.insertBReport(rcommunityDto);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //댓글 신고
    @PostMapping("/comment")
    public ResponseEntity<Void> reportComment(HttpServletRequest request, @RequestBody RcommentDto rcommentDto){
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);
        rcommentDto.setReporter(memberService.findById(memberNo).getMemberNickName());
        rcommentDto.setReportDate(new Date());
        try{
            reportService.insertCReport(rcommentDto);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //게시글 신고리스트 가져오기
    @GetMapping("/communitycount")
    public ResponseEntity<List<CommunityReportCountDto>> getCommunityReportCount(){
        List<CommunityReportCountDto> communityReportCountDto = reportService.communityReportCount();
        return ResponseEntity.status(HttpStatus.OK).body(communityReportCountDto);
    }

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

    @PostMapping("/deletePost")
    public ResponseEntity<Void> deletePost(@RequestBody DeletePostDto request) {
        reportService.deletePost(request.getReportId(), request.getAdminId(), request.getCommunityNo());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/blockAccount")
    public ResponseEntity<Void> blockAccount(@RequestBody BlockAccountDto request) {
        reportService.blockAccount(
                request.getReportId(),
                request.getAdminId(),
                request.getBlockReason()
        );
        return ResponseEntity.ok().build();
    }
}
