package io.paioneer.nain.community.controller;


import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import io.paioneer.nain.common.FileNameChange;
import io.paioneer.nain.common.Paging;
import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import io.paioneer.nain.community.model.dto.CommentDto;
import io.paioneer.nain.community.model.dto.CommunityDto;
import io.paioneer.nain.community.model.service.CommentService;
import io.paioneer.nain.community.model.service.CommunityService;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.member.model.service.MemberService;
import io.paioneer.nain.security.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/community")
@CrossOrigin
public class CommunityController {
    private final CommunityService communityService;
    private final CommentService commentService;

    private final CommunityDto communityDto;
    private final CommentDto commentDto;
    private final MemberService memberService;

    private final JWTUtil jwtUtil;

    private ResourceLoader resourceLoader;

    private OrderSpecifier<?> getOrderSpecifier(String sort) {
        PathBuilder<CommunityEntity> entityPath = new PathBuilder<>(CommunityEntity.class, "communityEntity");
        return switch (sort) {
            case "oldest" -> entityPath.getString("communityDate").asc();
            case "readCount" -> entityPath.getNumber("readCount", Long.class).desc();
            default -> entityPath.getString("communityDate").desc();
        };
    };

    private static Date TimeCalculate(){
        LocalDateTime localdateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime seoulTime = localdateTime.atZone(zoneId);
        return Date.from(seoulTime.toInstant());
    }
    //-------------------------- 목록 조회 -----------------------------------------------------------------------------------------------------
//    //전체 목록
//    @GetMapping("/list")
//    public ResponseEntity<Map<String, Object>> selectCommunityList(
//            @RequestParam(name="page") int page, @RequestParam(name="limit") int limit, @RequestParam(name="sort", defaultValue ="communityNo") String sort){
//        log.info("/community/list?page={}&limit={}&sort={}", page, limit, sort);
//        Pageable pageable = PageRequest.of(page - 1, limit);
//
//        Paging pg = new Paging(communityService.countCommunity(), page, limit);
//        pg.calculate();
//        ArrayList<CommunityDto> list = communityService.selectList(pageable, getOrderSpecifier(sort));
//        Map<String, Object> result = new HashMap();
//        result.put("list", list);
//        result.put("pg", pg);
//        log.info(TimeCalculate().toString());
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    //검색결과 목록
    @GetMapping("/search")
    public ResponseEntity<Map> selectSearchList(
            @RequestParam(name="keyword") String keyword, @RequestParam(name="type") String type,
            @RequestParam(name="page") int page, @RequestParam(name="limit") int limit, @RequestParam(name="sort", defaultValue ="communityNo") String sort){
        log.info("/community/search : keyword: {}, type: {}, page : {}, sort : {}", keyword, type, page, sort);
//        if(keyword == null){
//            keyword = "";
//        }
        Pageable pageable = PageRequest.of(page - 1, limit);
        Paging pg = new Paging(communityService.countSearchList(type, keyword, pageable), page, limit);
        pg.calculate();
        log.info("페이지 개수 : {}", pg.toString());
        Map<String, Object> result = new HashMap();
        result.put("list", communityService.selectSearchList(type, keyword, pageable, getOrderSpecifier(sort)));
        result.put("pg", pg);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //내 글 보기
    @GetMapping("/mylist")
    public ResponseEntity<String> myIist(HttpServletRequest request){
        log.info("내 정보 보기");
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);
        MemberDto loginMember = memberService.findById(memberNo);
        String writer = loginMember.getMemberNickName();
        return new ResponseEntity<>(writer, HttpStatus.OK);
    }


    //상세보기 ------------------------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/detail")
    public ResponseEntity<CommunityDto> selectCommunityDetail(@RequestParam(name="communityNo") Long communityNo){
        log.info("/community/detail{}", communityNo);
        CommunityDto communityDto = communityService.selectOne(communityNo);
        log.info(communityDto.toString());
        return new ResponseEntity<>(communityDto, HttpStatus.OK);
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

    //등록 ----------------------------------------------------------------------------------------------------------------------------------------------
    // 파일 등록
    @PostMapping("/file")
    public ResponseEntity<String> insertFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("파일 등록 : /community/{}", file);

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

//    // 이미지 등록
//    @PostMapping("/upload")
//    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) {
//        Map<String, String> response = new HashMap<>();
//        if (file.isEmpty()) {
//            response.put("url", "");
//            return response;
//        }
//
//        try {
//            byte[] bytes = file.getBytes();
//            Path resourcePath = Paths.get("src/main/resources/upload");
//            String savePath = resourcePath.toAbsolutePath().toString();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);
//            response.put("url", "/files/" + file.getOriginalFilename());
//        } catch (IOException e) {
//            e.printStackTrace();
//            response.put("url", "");
//        }
//
//        return response;
//    }

//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//        Path file = Paths.get(UPLOADED_FOLDER).resolve(filename);
//        Resource resource = new UrlResource(file.toUri());
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
//                .body(resource);
//    }

    //게시글 등록
    @PostMapping
    public ResponseEntity<Void> insertCommunity(HttpServletRequest request, @RequestBody CommunityDto community) throws IOException {
        log.info("게시글 등록 : /community/{}", community);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        community.setMemberDto(loginMember);
        community.setCommunityDate(TimeCalculate());
        log.info("등록 처리된 게시글 정보: {} ", community);
        communityService.insertCommunity(community);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    //수정 ----------------------------------------------------------------------------------------------------------------------------------------------------
    @PutMapping("/modify/{communityNo}")
    public ResponseEntity<Void> updateCommunity(
            HttpServletRequest request, @PathVariable("communityNo") Long communityNo, @RequestBody CommunityDto communityDto){
        log.info("게시글 수정 {}", communityDto);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        communityDto.setMemberDto(loginMember);
        communityDto.setModifiedDate(TimeCalculate());
        communityService.updateCommunity(communityDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //삭제 -------------------------------------------------------------------------------------------------------------------------------------------------------
    //삭제값 입력
    @PutMapping("/del/{communityNo}")
    public ResponseEntity<Void> deleteCommunity(HttpServletRequest request, @PathVariable(name="communityNo") Long communityNo, @RequestBody CommunityDto communityDto){
        log.info("/delete/{}", communityDto);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);

        if(communityDto.getWriter().equals(loginMember.getMemberNickName())) {
            communityDto.setMemberDto(loginMember);
            communityDto.setDeletedDate(TimeCalculate());
            log.info(communityDto.toString());
            communityService.deleteCommunity(communityDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    //글 DB 삭제
    @DeleteMapping("/terminate/{communityNo}")
    public ResponseEntity<Void> terminateCommunity(HttpServletRequest request, @PathVariable("communityNo") Long communityNo){
        log.info("/terminate/{}", communityNo);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);

        if(communityService.selectOne(communityNo).getWriter().equals(loginMember.getMemberNickName())) {
            communityService.terminateCommunity(communityNo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }


    //댓글 ---------------------------------------------------------------------------------------------------------------------------------------
    //상세보기_commentDto
    @GetMapping("/comment")
    public ResponseEntity<ArrayList<CommentDto>> selectCommentList(@RequestParam(name="communityNo") Long communityNo){
        log.info("댓글 목록_communityNo : {}", communityNo);
        ArrayList<CommentDto> list = commentService.selectList(communityNo);
        for(CommentDto comment : list){
            log.info(comment.getCommentNo().toString());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //댓글 등록
    @PostMapping("/comment")
    public ResponseEntity<Void> insertComment(HttpServletRequest request, @RequestBody CommentDto commentDto){
        log.info("댓글 입력 : {}", commentDto);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        commentDto.setMemberDto(loginMember);
        commentDto.setCommentDate(TimeCalculate());
        log.info("입력 데이터 최종: {}", commentDto);
        commentService.insertComment(commentDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //댓글 수정
    @PutMapping("/comment/modify/{commentNo}")
    public ResponseEntity<Void> modifyComment(HttpServletRequest request, @PathVariable(name="commentNo") Long commentNo, @RequestBody CommentDto commentDto){
        log.info("/community/detail/comment{}", commentDto);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        commentDto.setMemberDto(loginMember);
        commentDto.setModifiedDate(TimeCalculate());
        commentService.updateComment(commentDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //댓글 삭제값 추가
    @PutMapping("/comment/del/{commentNo}")
    public ResponseEntity<Void> deleteComment(@PathVariable(name="commentNo") Long commentNo, @RequestBody CommentDto commentDto){
        log.info("/community/del/comment{}", commentDto);
        commentDto.setDeletedDate(new Date());
        commentService.deleteComment(commentDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //댓글 DB 데이터 삭제
    @DeleteMapping("/comment/terminate/{commentNo}")
    public ResponseEntity<Void> terminateComment(@RequestBody Long commentNo){
        log.info("/community/terminate/comment{}", commentNo);
        commentService.terminateComment(commentNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
