package io.paioneer.nain.interview.voice.controller;


import io.paioneer.nain.interview.model.dto.QuestionDto;
import io.paioneer.nain.interview.voice.model.dto.VoiceDto;
import io.paioneer.nain.interview.voice.model.dto.VoiceSentenceDto;
import io.paioneer.nain.interview.voice.model.service.VoiceService;
import io.paioneer.nain.interview.voice.model.service.VoiceSentenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/voice")
@CrossOrigin
public class VoiceController {
    private final VoiceDto voiceDto;
    private final VoiceSentenceDto voiceSentenceDto;
    private final QuestionDto questionDto;

    private final VoiceService voiceService;
    private final VoiceSentenceService voiceSentenceService;

    //면접 번호 -> 질문 및 답변 개수(페이지 처리용), 답변
    @GetMapping("/voiceInfo")
    public ResponseEntity<Map> getAnswerCount(@RequestParam(name="itvNo") Long itvNo) {

        int count = voiceService.getAnswerCount(itvNo);
        ArrayList list = voiceService.getParagraph(itvNo);

        Map<String, Object> result = new HashMap<>();
        result.put("listCount", count);
        result.put("paragraph", list);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //답변 번호 -> 답변,  문장 리스트(Map)
    @GetMapping("/answers")
    public ResponseEntity<Map> getAnswers(@RequestParam(name="voiceNo") Long voiceNo) {

        String question = voiceService.getVoiceDto(voiceNo).getQuestionDto().getQContent();
        ArrayList list = voiceSentenceService.getAnswers(voiceNo);

        Map<String, Object> result = new HashMap<>();
        result.put("question", question);
        result.put("answers", list);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //문장 당 분석 내용(Map, 일정 수치만 리턴)
    @GetMapping("/analysis")
    public ResponseEntity<Map> getAnalysis(@RequestParam(name="vsNo") Long vsNo){

        Map<String, Object> result = new HashMap<>();

        VoiceSentenceDto voiceSentenceDto = voiceSentenceService.getAnalysis(vsNo);
        if(voiceSentenceDto.getPositive() > 60){
            result.put("sentiment", "positive");
            result.put("voiceSentenceDto", voiceSentenceDto);
        }else if(voiceSentenceDto.getNegative() > 60){
            result.put("sentiment", "negative");
            result.put("voiceSentenceDto", voiceSentenceDto);
        }else{
            result.put("sentiment", "neutral");
            result.put("voiceSentenceDto", voiceSentenceDto);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //결과_부정, 긍정 개수 및 문장 당 분석 평균
    @GetMapping("/total")
    public ResponseEntity<Map> getResults(@RequestParam(name="voiceNo") Long voiceNo){

        //60이상
        int positiveCounts = voiceSentenceService.getPositiveCount(voiceNo);
        int negativeCounts = voiceSentenceService.getNegativeCount(voiceNo);

        ArrayList<VoiceSentenceDto> list =  voiceSentenceService.getAnswers(voiceNo);
        float positiveTotal = 0.0f;
        float negativeTotal = 0.0f;
        for(VoiceSentenceDto voiceSentenceDto : list){
                positiveTotal += voiceSentenceDto.getPositive();
                negativeTotal += voiceSentenceDto.getNegative();
        }
        float positiveAvg = positiveTotal / list.size();
        float negativeAvg = negativeTotal / list.size();
        float neutralAvg = 1 - (positiveAvg + negativeAvg);

        Map<String, Object> result = new HashMap<>();
        result.put("positiveCounts", positiveCounts);
        result.put("negativeCounts", negativeCounts);
        result.put("positive", positiveAvg);
        result.put("negative", negativeAvg);
        result.put("neutral", neutralAvg);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
