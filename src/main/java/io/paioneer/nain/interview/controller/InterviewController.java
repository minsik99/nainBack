package io.paioneer.nain.interview.controller;

import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.interview.model.service.InterviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/interviewlist")
@CrossOrigin
public class InterviewController {

    private final InterviewService interviewService;
    @PostMapping
    public ResponseEntity<Void> insertInterview(@RequestBody Long memberNo) {
        interviewService.insertInterview(memberNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping("/list")
//    public ResponseEntity<List<InterviewDto>> selectInterviewList(String sotring) {
//        List<InterviewDto> interview = interviewService.selectInterviewList();
//
//        return new ResponseEntity<>(interview, HttpStatus.OK);
//    }


}
