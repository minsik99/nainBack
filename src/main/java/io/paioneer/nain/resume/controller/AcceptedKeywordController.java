package io.paioneer.nain.resume.controller;

import io.paioneer.nain.resume.model.dto.AcceptedKeywordDto;
import io.paioneer.nain.resume.model.service.AcceptedKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/acceptedkeyword")
@RequiredArgsConstructor
public class AcceptedKeywordController {
    @Autowired
    private AcceptedKeywordService service;

    // 직무별 직무 키워드 출력
    @GetMapping("/api/jobkeywords")
    public List<AcceptedKeywordDto> getJobKeywords(@RequestParam String jobCategory) {
        return service.getJobKeywordsByCategory(jobCategory);
    }

    // 직무별 요구 경력 출력
    @GetMapping("/api/expkeywords")
    public List<AcceptedKeywordDto> getExpKeywords(@RequestParam String jobCategory) {
        return service.getExpKeywordsByCategory(jobCategory);
    }

    // 기준 일자 출력
    @GetMapping("/api/referenceDate")
    public ResponseEntity<Date> getReferenceDate(@RequestParam String jobCategory) {
        try {
            Date referenceDate = service.getLatestReferenceDate(jobCategory);
            return ResponseEntity.ok(referenceDate);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
