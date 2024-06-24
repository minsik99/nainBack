package io.paioneer.nain.notice.controller;

import io.paioneer.nain.common.Paging;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.member.model.service.MemberService;
import io.paioneer.nain.notice.model.dto.NoticeDto;
import io.paioneer.nain.notice.model.service.NoticeService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@CrossOrigin
public class NoticeController {
    private final NoticeService noticeService;

    private final MemberService memberService;

    private final JWTUtil jwtUtil;

    //전체 목록 출력
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>>  selectNoticeList(@RequestParam(name = "page")int page,
                                                           @RequestParam(name = "limit")int limit, @RequestParam(name = "sort", defaultValue ="noticeNo") String sort){

        //페이지에 출력할 목록 조회해 옴 => 응답 처리
        log.info("/notice/list?page={}&limit={}&sort={}", page, limit, sort);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, sort));
        Paging pg = new Paging(noticeService.countNotice(), page, limit);
        log.info(pg.toString());
        pg.calculate();
        log.info(pg.toString());
        log.info(pageable.toString());
        ArrayList<NoticeDto> list = noticeService.selectNoticeList(pageable);
        log.info(list.toString());
        Map<String, Object> result = new HashMap();
        result.put("list", list);
        result.put("pg", pg);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    //글 1개 상세보기
    @GetMapping("/detail/{noticeNo}")
    public ResponseEntity<NoticeDto> selectNoticeOne(@PathVariable("noticeNo") Long noticeNo){
        log.info("/notice/detail{}+" + noticeNo);
        return new ResponseEntity<>(noticeService.selectNoticeOne(noticeNo), HttpStatus.OK);
    }

    //등록
    @PostMapping
    public ResponseEntity<Void> insertNotice(HttpServletRequest request, @RequestBody NoticeDto noticeDto, @RequestParam(required = false) MultipartFile file) throws IOException{
        log.info("insertNotice : " + noticeDto);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        noticeService.insertNotice(noticeDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //수정
    @PutMapping("/{noticeNo}")
    public ResponseEntity<Void> updateNotice(
            @PathVariable("noticeNo") Long noticeNo, @RequestBody NoticeDto noticeDto){
        log.info("updateNotice : " + noticeNo);
        noticeService.updateNotice(noticeDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //삭제
    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<Void> deleteNotice(@PathVariable("noticeNo") Long noticeNo){
        log.info("deleteNotice : " + noticeNo);
        noticeService.deleteNotice(noticeNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //제목 검색
    @GetMapping("/noticeTitle")
    public ResponseEntity<List<NoticeDto>> selectSearchTitle(@RequestParam(name = "keyword") String keyword, @RequestParam(name = "page") int page,
                                                             @RequestParam(name = "limit") int limit){

        log.info("/notice/noticeTitle:" + keyword + ","+ page + limit);
        //JPA가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page -1, limit, Sort.by(Sort.Direction.DESC, "noticeNo"));
        //페이지에 출력할 목록 조회해 옴 => 응답 처리
        return new ResponseEntity<>(noticeService.selectSearchNoticeTitle(keyword, pageable), HttpStatus.OK);

    }

    //내용 검색
    @GetMapping("/noticeContent")
    public ResponseEntity<List<NoticeDto>> selectSearchContent(@RequestParam(name = "keyword")String keyword, @RequestParam(name = "page")int page,
                                                               @RequestParam(name = "limit")int limit){

        log.info("/notice/noticeContent:" + page + "," + limit);
        //JPA가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page -1, limit, Sort.by(Sort.Direction.DESC, "noticeNo"));
        //페이지에 출력할 목록 조회해 옴 => 응답 처리
        return new ResponseEntity<>(noticeService.selectSearchNoticeContent(keyword,pageable), HttpStatus.OK);
    }

    //작성자 검색
    @GetMapping("/noticeWriter")
    public ResponseEntity<List<NoticeDto>> selectSearchWriter(@RequestParam(name = "keyword")String keyword,
                                                                         @RequestParam(name = "page")int page,
                                                                         @RequestParam(name = "limit")int limit){

        log.info("/notice/noticeWriter:" + page + "," + limit);
        //JPA가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page -1, limit, Sort.by(Sort.Direction.DESC, "noticeNo"));
        //페이지에 출력할 목록 조회해 옴 => 응답 처리
        return new ResponseEntity<>(noticeService.selectSearchNoticeWriter(keyword, pageable), HttpStatus.OK);

    }

    //제목 검색 목록 갯수 조회용
    @GetMapping("/countSearchTitle")
    public ResponseEntity<Long> getSearchNoticeTitleCount(@RequestParam(name ="keyword") String keyword){
        return new ResponseEntity<>(noticeService.getSearchNoticeTitleCount(keyword),HttpStatus.OK);
    }


    //내용 검색 목록 갯수 조회용
    @GetMapping("/countSearchContent")
    public ResponseEntity<Long> getSearchNoticeContentCount(@RequestParam(name ="keyword") String keyword){
        return new ResponseEntity<>(noticeService.getSearchNoticeContentCount(keyword),HttpStatus.OK);
    }

    //등록날짜 검색 목록 갯수 조회용
    @GetMapping("/countSearchDate")
    public ResponseEntity<Long> getSearchNoticeDateCount(@RequestParam(name ="keyword") String keyword,
                                                         @RequestParam(name = "page")int page,
                                                         @RequestParam(name = "limit")int limit) {
        //JPA가 제공하는 Pageable 객체를 사용함
        Pageable pageable = PageRequest.of(page -1, limit, Sort.by(Sort.Direction.DESC, "noticeNo"));
        return new ResponseEntity<>(noticeService.getSearchNoticeWriterCount(keyword, pageable),HttpStatus.OK);
    }




}

