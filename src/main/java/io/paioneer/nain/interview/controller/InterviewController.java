package io.paioneer.nain.interview.controller;

import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.interview.model.service.InterviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/interview")
@CrossOrigin
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping("/{memberNo}")
    public ResponseEntity<Long> insertInterview(@PathVariable(name="memberNo") Long memberNo) {
        return new ResponseEntity<>(interviewService.insertInterview(memberNo), HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<Page<InterviewDto>> selectInterviewList(@RequestParam(name="page") int page,
                                                                  @RequestParam(name="size") int size, @RequestParam(name="memberNo") String memberNo) {
        Pageable pageable = PageRequest.of(page, size);
        Page<InterviewDto> interview = interviewService.selectInterviewList(Long.parseLong(memberNo), pageable);
        return new ResponseEntity<>(interview, HttpStatus.OK);
    }

    @DeleteMapping("/list")
    public ResponseEntity<?> deleteInterview(@RequestParam(name="itvNo") Long itvNo){
        log.info(itvNo.toString());
        interviewService.deleteInterview(itvNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
