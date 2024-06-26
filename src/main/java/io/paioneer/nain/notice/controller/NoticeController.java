package io.paioneer.nain.notice.controller;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import io.paioneer.nain.common.FileNameChange;
import io.paioneer.nain.common.Paging;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.member.model.service.MemberService;
import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
import io.paioneer.nain.notice.model.dto.NoticeDto;
import io.paioneer.nain.notice.model.service.NoticeService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@CrossOrigin
public class NoticeController {
    private final NoticeService noticeService;

    private final MemberService memberService;

    private final JWTUtil jwtUtil;

//    private OrderSpecifier<?> getOrderSpecifier(String sort) {
//        PathBuilder<NoticeEntity> entityPath = new PathBuilder<>(NoticeEntity.class, "noticeEntity");
//        return switch (sort) {
//            case "oldest" -> entityPath.getString("noticeDate").asc();
//            case "readCount" -> entityPath.getNumber("noticeReadCount", Long.class).desc();
//            default -> entityPath.getString("noticeDate").desc();
//        };
//    };
//
//    private static Date TimeCalculate(){
//        LocalDateTime localdateTime = LocalDateTime.now();
//        ZoneId zoneId = ZoneId.of("Asia/Seoul");
//        ZonedDateTime seoulTime = localdateTime.atZone(zoneId);
//        return Date.from(seoulTime.toInstant());
//    }

    //전체 목록 출력
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> selectNoticeList(@RequestParam(name = "page")int page,
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
    @GetMapping("/detail")
    public ResponseEntity<NoticeDto> selectNoticeOne(@RequestParam(name = "noticeNo") Long noticeNo){
        log.info("/notice/detail{}", noticeNo);

        NoticeDto noticeDto = noticeService.selectNoticeOne(noticeNo);
        // memberDto가 null인 경우 처리
        if (noticeDto.getMemberDto() == null) {
            // 새로운 MemberDto 객체 생성 또는 기본값 설정
            noticeDto.setMemberDto(new MemberDto());
        }

        log.info(noticeDto.toString());
        return new ResponseEntity<>(noticeDto, HttpStatus.OK);
    }

    //파일 불러오기
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam(name = "fileName") String fileName) {
        try {
            log.info("파일명 : {} ", fileName);
            Path resourcePath = Paths.get("src/main/resources/upload");
            String savePath = resourcePath.toAbsolutePath().toString();
            log.info(savePath);
            String filePath = savePath + File.separator + fileName;
            File file = new File(filePath);

            if (!file.exists()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Resource resource = new FileSystemResource(file);

            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //등록
    @PostMapping
    public ResponseEntity<Void> insertNotice(HttpServletRequest request, @RequestBody NoticeDto noticeDto) throws IOException{
        log.info("insertNotice : " + noticeDto);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        noticeService.insertNotice(noticeDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 파일 등록
    @PostMapping("/file")
    public ResponseEntity<String> insertFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("파일 등록 : /notice/{}", file);

        if(file != null && !file.isEmpty()) {
            Path resourcePath = Paths.get("src/main/resources/upload");
            String savePath = resourcePath.toAbsolutePath().toString();
            log.info(savePath);
            File uploadDir = new File(savePath);
            String fileName = file.getOriginalFilename();
            String fileRename = FileNameChange.change(fileName, "yyyyMMddHHmmss");
            File saveFile = new File(uploadDir, fileRename);
            file.transferTo(saveFile);
            return new ResponseEntity<>(fileRename, HttpStatus.CREATED);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    //수정
    @PutMapping("/{noticeNo}")
    public ResponseEntity<Void> updateNotice(
            @PathVariable("noticeNo") Long noticeNo, @RequestBody NoticeDto noticeDto){
        log.info("updateNotice : " + noticeNo);
        noticeService.updateNotice(noticeDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //삭제
    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<Void> deleteNotice(HttpServletRequest request, @PathVariable("noticeNo") Long noticeNo){
        log.info("deleteNotice : " + noticeNo);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);

        if(noticeService.selectNoticeOne(noticeNo).getNoticeWriter().equals(loginMember.getMemberNickName())) {
            noticeService.deleteNotice(noticeNo);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

