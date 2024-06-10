package io.paioneer.nain.community.controller;

import io.paioneer.nain.common.FileNameChange;
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
import java.util.ArrayList;
import java.util.Date;

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

    //-------------------------- 목록 조회 -----------------------------------------------------------------------------------------------------
    //전체 목록
    @GetMapping("/list")
    public ResponseEntity<ArrayList<CommunityDto>> selectCommunityList(
            @RequestParam(name="page") int page, @RequestParam(name="limit") int limit, @RequestParam(name="Sort") String sort){
        log.info("/community/list{}, {}, {}", page, limit, sort);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, sort));
        return new ResponseEntity<>(communityService.selectList(pageable), HttpStatus.OK);
    }

    //내 글 보기
    @GetMapping("/mylist")
    public ResponseEntity<ArrayList<CommunityDto>> selectMyList(
            HttpServletRequest request, @RequestParam(name="page") int page,
            @RequestParam(name="limit") int limit, @RequestParam(name="Sort") String sort){
        log.info("/community/mylist{}, {}, {}", page, limit, sort);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, sort));
        return new ResponseEntity<>(communityService.selectMyList(memberNo, pageable), HttpStatus.OK);
    }

    //검색결과 목록
    @GetMapping("/search/{keyword}")
    public ResponseEntity<ArrayList<CommunityDto>> selectSearchList(
            @PathVariable("keyword") String keyword, @RequestParam(name="type") String type,
            @RequestParam(name="page") int page, @RequestParam(name="limit") int limit, @RequestParam(name="Sort") String sort){
        log.info("/community/search{}, {}, {}", keyword, type, page, sort);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, sort));
        return new ResponseEntity<>(communityService.selectSearchList(type, keyword, pageable), HttpStatus.OK);
    }

    //상세 보기 ---------------------------------------------------------------------------------------------------------------------------------------
    //상세보기_communityDto
    @GetMapping("/detail/{communityNo}")
    public ResponseEntity<CommunityDto> selectCommunityDetail(@PathVariable(name="communityNo") Long communityNo){
        log.info("/community/detail{}", communityNo);
        return new ResponseEntity<>(communityService.selectOne(communityNo), HttpStatus.OK);
    }

    //상세보기_commentDto
    @GetMapping("/detail/comments/{communityNo}")
    public ResponseEntity<ArrayList<CommentDto>> selectCommentList(@PathVariable(name="communityNo") Long communityNo){
        log.info("/community/detail/comments/{}", communityNo);
        return new ResponseEntity<>(commentService.selectList(communityNo), HttpStatus.OK);
    }

    //댓글 등록
    @PostMapping("/comment/new")
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
    @PutMapping("/comment/modify")
    public ResponseEntity<Void> modifyComment(HttpServletRequest request, @RequestBody CommentDto commentDto){
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
    @PutMapping("/comment/del")
    public ResponseEntity<Void> deleteComment(@RequestBody CommentDto commentDto){
        log.info("/community/del/comment{}", commentDto);
        commentService.deleteComment(commentDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //댓글 DB 데이터 삭제
    @DeleteMapping("/comment/{commentNo}")
    public ResponseEntity<Void> terminateComment(@RequestBody Long commentNo){
        log.info("/community/terminate/comment{}", commentNo);
        commentService.terminateComment(commentNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    //등록 ----------------------------------------------------------------------------------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<Void> insertCommunity(HttpServletRequest request, @RequestParam(name="community") CommunityDto community, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("/community/{}", community);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);
        if(loginMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(!file.isEmpty()){
            String savePath = request.getServletContext().getRealPath("/community_files");
            String fileName = file.getOriginalFilename();
            String fileRename = FileNameChange.change(fileName, "yyyyMMddHHmmdd");
            File saveFile = new File(savePath + "\\" + fileRename);
            file.transferTo(saveFile);

            communityDto.setFileUpload(fileName);
            communityDto.setFileModified(fileRename);
        }
        communityDto.setMemberDto(loginMember);
        communityService.insertCommunity(communityDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //수정 ----------------------------------------------------------------------------------------------------------------------------------------------------
    @PutMapping("/{communityNo}")
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
    @PutMapping("/community/del")
    public ResponseEntity<Void> deleteCommunity(HttpServletRequest request, @RequestBody CommunityDto communityDto){
        log.info("/delete/{}", communityDto);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberNoFromToken(token);

        MemberDto loginMember = memberService.findById(memberNo);

        if(communityDto.getWriter().equals(loginMember.getMemberNickName())) {
            communityService.deleteCommunity(communityDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    //댓글 DB 삭제
    @DeleteMapping("/{communityNo}")
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

}
