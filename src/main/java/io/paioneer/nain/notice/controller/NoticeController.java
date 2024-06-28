package io.paioneer.nain.notice.controller;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import io.paioneer.nain.common.FileNameChange;
import io.paioneer.nain.common.Paging;
import io.paioneer.nain.common.TimeFormater;
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

    //정렬순 설정
    private OrderSpecifier<?> getOrderSpecifier(String sort) {
        PathBuilder<NoticeEntity> entityPath = new PathBuilder<>(NoticeEntity.class, "noticeEntity");
        return switch (sort) {
            case "oldest" -> entityPath.getString("noticeDate").asc();
            case "noticeReadCount" -> entityPath.getNumber("noticeReadCount", Long.class).desc();
            default -> entityPath.getString("noticeDate").desc();
        };
    };

    private static Date TimeCalculate(){
        LocalDateTime localdateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime seoulTime = localdateTime.atZone(zoneId);
        return Date.from(seoulTime.toInstant());
    }

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
    public ResponseEntity<Resource> downloadNoticeFile(@RequestParam(name = "fileName") String fileName) {
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
    public ResponseEntity<Long> insertNotice(HttpServletRequest request, @RequestBody NoticeDto noticeDto) throws IOException{
        log.info("insertNotice : " + noticeDto);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        noticeDto.setMemberDto(loginMember);
        noticeDto.setNoticeDate(TimeCalculate());
        log.info("등록 처리된 게시글 정보: {} ", noticeDto);
        Long noticeNo = noticeService.insertNotice(noticeDto);

        return new ResponseEntity<>(noticeNo, HttpStatus.CREATED);
    }

    // 파일 등록
    @PostMapping("/file")
    public ResponseEntity<String> insertNoticeFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
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
    @PutMapping("/modify/{noticeNo}")
    public ResponseEntity<Void> updateNotice(HttpServletRequest request,
            @PathVariable("noticeNo") Long noticeNo, @RequestBody NoticeDto noticeDto){
        log.info("updateNotice : " + noticeNo);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        noticeDto.setMemberDto(loginMember);
        noticeDto.setNoticeModify(TimeCalculate());
        noticeService.updateNotice(noticeDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //삭제값 입력
    @PutMapping("/del/{noticeNo}")
    public ResponseEntity<Void> hiddenNotice(HttpServletRequest request, @PathVariable(name="noticeNo") Long noticeNo, @RequestBody NoticeDto noticeDto){
        log.info("/delete/{}", noticeDto);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);

        if(noticeDto.getNoticeWriter().equals(loginMember.getMemberNickName())) {
            noticeDto.setMemberDto(loginMember);
            noticeDto.setNoticeDelete(TimeFormater.TimeCalculate());
            log.info(noticeDto.toString());
            noticeService.hiddenNotice(noticeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    //DB 삭제
    @DeleteMapping("/delete/{noticeNo}")
    public ResponseEntity<Void> deleteNotice(HttpServletRequest request, @PathVariable("noticeNo") Long noticeNo){
        log.info("deleteNotice : " + noticeNo);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);

        if(noticeService.selectNoticeOne(noticeNo).getNoticeWriter().equals(loginMember.getMemberNickName())) {
            noticeService.deleteNotice(noticeNo);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
//    //검색
//    @GetMapping("/search")
//    public ResponseEntity<Map<String, Object>> selectSearchNotice(
//            @RequestParam(name = "keyword") String keyword,
//            @RequestParam(name = "page") int page,
//            @RequestParam(name = "limit") int limit,
//            @RequestParam(name = "type") String type
//    ) {
//        log.info("/notice/search: keyword={}, page={}, limit={}, type={}", keyword, page, limit, type);
//
//        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "noticeNo"));
//        Paging pg = new Paging(noticeService.countSearchNotice(type, keyword, pageable), page, limit);
//        pg.calculate();
//
//        Map<String, Object> response;
//        try {
//            response = noticeService.selectSearchNotice(keyword, pageable, type);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    //검색결과 목록
    @GetMapping("/search")
    public ResponseEntity<Map> selectSearchNotice(
            @RequestParam(name="keyword") String keyword, @RequestParam(name="type") String type,
            @RequestParam(name="page") int page, @RequestParam(name="limit") int limit, @RequestParam(name="sort", defaultValue ="noticeNo") String sort){
        log.info("/notice/search : keyword: {}, type: {}, page : {}, sort : {}", keyword, type, page, sort);
        Pageable pageable = PageRequest.of(page - 1, limit);
        Paging pg = new Paging(noticeService.countSearchNotice(type, keyword, pageable), page, limit);
        pg.calculate();
        log.info("페이지 개수 : {}", pg.toString());
        Map<String, Object> result = new HashMap();
        result.put("list", noticeService.selectSearchNotice(type, keyword, pageable, getOrderSpecifier(sort)));
        result.put("pg", pg);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}

