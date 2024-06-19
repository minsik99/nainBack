package io.paioneer.nain.community.controller;

import com.querydsl.core.types.Order;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    private OrderSpecifier<?> getOrderSpecifier(String sort) {
        PathBuilder<CommunityEntity> entityPath = new PathBuilder<>(CommunityEntity.class, "communityEntity");
        return switch (sort) {
            case "oldest" -> entityPath.getString("communityDate").asc();
            case "readCount" -> entityPath.getString("readCount").desc();
            default -> entityPath.getString("communityDate").desc();
        };
    };

    //-------------------------- 목록 조회 -----------------------------------------------------------------------------------------------------
    //전체 목록
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> selectCommunityList(
            @RequestParam(name="page") int page, @RequestParam(name="limit") int limit, @RequestParam(name="sort", defaultValue ="communityNo") String sort){
        log.info("/community/list?page={}&limit={}&sort={}", page, limit, sort);
        Pageable pageable = PageRequest.of(page - 1, limit);

        Paging pg = new Paging(communityService.countCommunity(), page, limit);
        pg.calculate();
        log.info(pg.toString());
        ArrayList<CommunityDto> list = communityService.selectList(pageable, getOrderSpecifier(sort));
        log.info(list.toString());
        Map<String, Object> result = new HashMap();
        result.put("list", list);
        result.put("pg", pg);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //내 글 보기
    @GetMapping("/mylist")
    public ResponseEntity<Map> selectMyList(
            HttpServletRequest request, @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit, @RequestParam(name="sort", defaultValue ="communityDate") String sort){
        log.info("/community/mylist{}, {}, {}", page, limit, sort);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);
        MemberDto loginMember = memberService.findById(memberNo);

        Pageable pageable = PageRequest.of(page - 1, limit);

        Paging pg = new Paging(communityService.countMyList(memberNo, getOrderSpecifier(sort)), page, limit);
        pg.calculate();

        Map<String, Object> result = new HashMap();
        result.put("list", communityService.selectSearchList("writer", loginMember.getMemberNickName(), pageable, getOrderSpecifier(sort)));
        result.put("pg", pg);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //검색결과 목록
    @GetMapping("/search")
    public ResponseEntity<Map> selectSearchList(
            @RequestParam(name="keyword") String keyword, @RequestParam(name="type") String type,
            @RequestParam(name="page") int page, @RequestParam(name="limit") int limit, @RequestParam(name="sort", defaultValue ="communityNo") String sort){
        log.info("/community/search{}, {}, {}", keyword, type, page, sort);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, sort));
        Paging pg = new Paging(communityService.countSearchList(type, keyword, pageable, getOrderSpecifier(sort)), page, limit);
        pg.calculate();

        Map<String, Object> result = new HashMap();
        result.put("list", communityService.selectSearchList(type, keyword, pageable, getOrderSpecifier(sort)));
        result.put("pg", pg);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    //상세보기 ------------------------------------------------------------------------------------------------------------------------------------------------------
    @GetMapping("/detail")
    public ResponseEntity<CommunityDto> selectCommunityDetail(@RequestParam(name="communityNo") Long communityNo){
        log.info("/community/detail{}", communityNo);
        return new ResponseEntity<>(communityService.selectOne(communityNo), HttpStatus.OK);
    }

    //등록 ----------------------------------------------------------------------------------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<Void> insertCommunity(HttpServletRequest request, @RequestBody CommunityDto community, @RequestParam(required = false) MultipartFile file) throws IOException {
        log.info("/community/{}", community);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(file != null && !file.isEmpty()){
            String savePath = request.getServletContext().getRealPath("/community_files");
            String fileName = file.getOriginalFilename();
            String fileRename = FileNameChange.change(fileName, "yyyyMMddHHmmdd");
            File saveFile = new File(savePath + "\\" + fileRename);
            file.transferTo(saveFile);

            community.setFileUpload(fileName);
            community.setFileModified(fileRename);
        }
        community.setMemberDto(loginMember);
        community.setCommunityDate(new Date());
        log.info(community.toString());
        communityService.insertCommunity(community);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //수정 ----------------------------------------------------------------------------------------------------------------------------------------------------
    @PutMapping("/modify/{communityNo}")
    public ResponseEntity<Void> updateCommunity(
            HttpServletRequest request, @PathVariable("communityNo") Long communityNo, @RequestBody CommunityDto communityDto){
        log.info("/update/{}", communityDto);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        communityDto.setMemberDto(loginMember);
        communityDto.setModifiedDate(new Date());
        communityService.updateCommunity(communityDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //삭제 -------------------------------------------------------------------------------------------------------------------------------------------------------
    //삭제값 입력
    @PutMapping("/del/{communityNo}")
    public ResponseEntity<Void> deleteCommunity(HttpServletRequest request, @PathVariable(name="communityNo") Long communityNo,@RequestBody CommunityDto communityDto){
        log.info("/delete/{}", communityDto);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);

        if(communityDto.getWriter().equals(loginMember.getMemberNickName())) {
            communityDto.setDeletedDate(new Date());
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
        log.info("/community/detail/comments/{}", communityNo);
        ArrayList<CommentDto> list = commentService.selectList(communityNo);
        log.info(list.toString());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //댓글 등록
    @PostMapping("/comment")
    public ResponseEntity<Void> insertComment(HttpServletRequest request, @RequestBody CommentDto commentDto){
        log.info("/community/detail/comment{}", commentDto);

        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        commentDto.setMemberNo(memberNo);

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

        commentDto.setMemberNo(memberNo);
        commentDto.setModifiedDate(new Date());
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
