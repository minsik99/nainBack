package io.paioneer.nain.community.controller;

import io.paioneer.nain.common.FileNameChange;
import io.paioneer.nain.community.model.dto.CommentDto;
import io.paioneer.nain.community.model.dto.CommunityDto;
import io.paioneer.nain.community.model.service.CommentService;
import io.paioneer.nain.community.model.service.CommunityService;
import io.paioneer.nain.member.model.dto.MemberDto;
import io.paioneer.nain.member.model.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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


    //-------------------------- 목록 조회 ------------------------------------------------------------
    //전체 목록
    @GetMapping("/list")
    public ResponseEntity<ArrayList<CommunityDto>> selectCommunitylList(@RequestParam(name="page") int page, @RequestParam(name="limit") int limit){
        log.info("/community/list{}, {}", page, limit);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "communityNo"));
        return new ResponseEntity<>(communityService.selectList(pageable), HttpStatus.OK);
    }

    //내 글 보기
    @GetMapping("/mylist")
    public Response<ArrayList<CommunityDto>> selectMyList(HttpServletRequest request,
                                                          @RequestParam(name="page") int page, @RequestParam(name="limit") int limit){
        String token = request.getHeader("Authorization").substring("Bearer".length());
        Long memberNo = Long memberNo =  jwtUtil.getMemberIdFromToken(token);

        Pageable pageable = PageRequest.of(page - 1, limit. Sort.by(Sort.Direction.DESC, "communityNo"));
        return new ResponseEntity<>(communityService.selectMyList(memberNo, pageable), HttpStatus.OK);
    }

    //검색결과 목록
//    @GetMapping("/search/{keyword}")
//    public Response<ArrayList<CommunityDto>> selectMyList(@PathVariable("keyword") String keyword,
//                                                          @RequestParam(name="page") int page, @RequestParam(name="limit") int limit){
//
//        Pageable pageable = PageRequest.of(page - 1, limit. Sort.by(Sort.Direction.DESC, "communityDate"));
//        return new ResponseEntity<>(communityService.selectSearchList(keyword, pageable), HttpStatus.OK);
//    }

    //목록 표시 개수 변경

    //정렬 기준 변경

    //상세 보기 -----------------------------------------------------------------------------------------
    @GetMapping("/detail")
    public ResponseEntity<CommunityDto> selectCommunityDetail(@RequestParam(name="communityNo") Long communityNo){
        log.info("/community/detail{}, {}", communityNo);
        return new ResponseEntity<>(communityService.selectOne(communityNo), HttpStatus.OK);
    }

    //등록 ----------------------------------------------------------------------------------------------------------------------------------------------
    @PostMapping
    public ResponseEntity<> insertCommunity(HttpServletRequest request, @RequestParam(name="community") CommunityDto community, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("/community/{}", community);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo =  jwtUtil.getMemberIdFromToken(token);

        MemberDto loginMember = memberService.selectMember(memberNo);
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
    public ResponseEntity<Void> updateCommunity(@PathVariable("communityNo") Long communityNo, @RequestBody CommunityDto communityDto){
        log.info("/update/{}", communityDto);
        communityService.updateCommunity(communityDto);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //삭제 -------------------------------------------------------------------------------------------------------------------------------------------------------
    @DeleteMapping("/{communityNo")
    public ResponseEntity<Void> deleteCommunity(HttpServletRequest request, @PathVariable("communityNo") Long communityNo){
        log.info("/delete/{}", communityNo);
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long memberNo = jwtUtil.getMemberIdFromToken(token);

        MemberDto loginMember = memberService.selectMember(memberNo);

        if(communityService.selectOne(communityNo).getWriter().equals(loginMember.getMemberNickName())) {
            communityService.deleteCommunity(communityNo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

}
