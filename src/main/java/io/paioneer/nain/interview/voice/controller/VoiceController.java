package io.paioneer.nain.interview.voice.controller;


import io.paioneer.nain.interview.model.dto.QuestionDto;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;
import io.paioneer.nain.interview.voice.model.dto.VoiceDto;
import io.paioneer.nain.interview.voice.model.dto.VoiceSentenceDto;
import io.paioneer.nain.interview.voice.model.service.VoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/interview/voice")
@CrossOrigin
public class VoiceController {

    private final VoiceService voiceService;

    //Voice_시행한 면접 기록_질문 목록
    @GetMapping("/questions/{itvNo}")
    public ResponseEntity<ArrayList<VoiceDto>> selectRecord(@PathVariable(name="itvNo") Long itvNo){
        log.info("면접 번호 {}", itvNo);
        ArrayList<VoiceDto> qnaList = voiceService.selectRecord(itvNo);
        log.info("질문리스트 {}", qnaList);
        return new ResponseEntity<>(qnaList,HttpStatus.OK);
    }

    //Voice_시행한 면접 기록_답변 문장
    @GetMapping("/answers/{voiceNo}")
    public ResponseEntity<ArrayList<VoiceSentenceDto>> selectSentence(@PathVariable(name="voiceNo") Long voiceNo){
        log.info("답변 번호 {}", voiceNo);

        ArrayList<VoiceSentenceDto> answerList = voiceService.selectAnswer(voiceNo);
        log.info("답변리스트 {}", answerList);
        return new ResponseEntity<>(answerList,HttpStatus.OK);
    }

//    //문장 당 분석 내용(Map, 일정 수치만 리턴)
//    @GetMapping("/analysis")
//    public ResponseEntity<Map> getAnalysis(@RequestParam(name="vsNo") Long vsNo){
//
//        Map<String, Object> result = new HashMap<>();
//
//        VoiceSentenceDto voiceSentenceDto = voiceService.getAnalysis(vsNo);
//        if(voiceSentenceDto.getPositive() > 60){
//            result.put("sentiment", "positive");
//            result.put("voiceSentenceDto", voiceSentenceDto);
//        }else if(voiceSentenceDto.getNegative() > 60){
//            result.put("sentiment", "negative");
//            result.put("voiceSentenceDto", voiceSentenceDto);
//        }else{
//            result.put("sentiment", "neutral");
//            result.put("voiceSentenceDto", voiceSentenceDto);
//        }
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    //결과_부정, 긍정 개수 및 문장 당 분석 평균
//    @GetMapping("/total")
//    public ResponseEntity<Map> getResults(@RequestParam(name="voiceNo") Long voiceNo){
//
//        //60이상
//        int positiveCounts = voiceService.getPositiveCount(voiceNo);
//        int negativeCounts = voiceService.getNegativeCount(voiceNo);
//
//        ArrayList<VoiceSentenceDto> list =  voiceService.getAnswers(voiceNo);
//        float positiveTotal = 0.0f;
//        float negativeTotal = 0.0f;
//        for(VoiceSentenceDto voiceSentenceDto : list){
//                positiveTotal += voiceSentenceDto.getPositive();
//                negativeTotal += voiceSentenceDto.getNegative();
//        }
//        float positiveAvg = positiveTotal / list.size();
//        float negativeAvg = negativeTotal / list.size();
//        float neutralAvg = 1 - (positiveAvg + negativeAvg);
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("positiveCounts", positiveCounts);
//        result.put("negativeCounts", negativeCounts);
//        result.put("positive", positiveAvg);
//        result.put("negative", negativeAvg);
//        result.put("neutral", neutralAvg);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}
