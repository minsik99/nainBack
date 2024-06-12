package io.paioneer.nain.report.controller;

import io.paioneer.nain.member.model.service.MemberService;
import io.paioneer.nain.report.model.dto.RcommentDto;
import io.paioneer.nain.report.model.dto.RcommunityDto;
import io.paioneer.nain.report.model.service.ReportService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
}
