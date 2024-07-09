package io.paioneer.nain.resume.controller;

import io.paioneer.nain.resume.model.dto.AcceptedKeywordDto;
import io.paioneer.nain.resume.model.service.AcceptedKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/acceptedkeyword")
@RequiredArgsConstructor
public class AcceptedKeywordController {
    @Autowired
    private AcceptedKeywordService service;

    @GetMapping("/api/keywords")
    public List<AcceptedKeywordDto> getKeywords(@RequestParam String jobCategory) {
        return service.getKeywordsByCategory(jobCategory);
    }
}
