package io.paioneer.nain.interview.video.controller;

import io.paioneer.nain.interview.jpa.entity.InterviewEntity;
import io.paioneer.nain.interview.model.dto.InterviewDto;
import io.paioneer.nain.interview.model.service.InterviewService;
import io.paioneer.nain.interview.video.model.service.EmotionService;
import io.paioneer.nain.interview.video.model.service.VideoService;
import io.paioneer.nain.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/video")
@CrossOrigin
public class VideoController {

    private final VideoService videoService;
    private final EmotionService emotionService;
    private final InterviewService interviewService;
    private final MemberService memberService;

    @GetMapping("/emotions")
    public ResponseEntity<Map<String, Double>> getEmotionAverages(@RequestParam(name="itvNo") Long itvNo) {
        Map<String, Double> emotionAverages = emotionService.calculateEmotionAverages(itvNo);
        return new ResponseEntity<>(emotionAverages, HttpStatus.OK);
    }

    @GetMapping("/posEye")
    public ResponseEntity<Map<String, List<Double>>> getPosEyeAnalysis(@RequestParam(name="itvNo") Long itvNo) {
        Map<String, List<Double>> posEyeAnalysis = videoService.selectListPosEyeAnaly(itvNo);
        return new ResponseEntity<>(posEyeAnalysis, HttpStatus.OK);
    }

    @GetMapping("/averageScores")
    public ResponseEntity<Map<Integer, Double>> getAverageScores(@RequestParam(name="itvNo") Long itvNo) {
        try {
            log.info("평균 불러오기{}", itvNo);
            Map<Integer, Double> avgScores = videoService.getAverageScores(itvNo);
            return new ResponseEntity<>(avgScores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/totalVideo")
    public ResponseEntity<Double> getTotalVideo(@RequestParam(name="itvNo") Long itvNo) {
        try {
            Map<Integer, Double> avgScores = videoService.getAverageScores(itvNo);

            double totalScore = avgScores.values().stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);
            log.info("Total Score: {}", totalScore);
            BigDecimal roundedScore = new BigDecimal(totalScore).setScale(2, RoundingMode.HALF_UP);
            double finalScore = roundedScore.doubleValue();
            return new ResponseEntity<>(finalScore, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while processing totalVideo request", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateTotalVideo")
    public ResponseEntity<Double> getTotalVideo(@RequestParam(name="itvNo") Long itvNo, @RequestParam(name="finalScore") double finalScore) {
        try {
            InterviewDto interviewDto = interviewService.selectInterview(itvNo);
            log.info("finalScore: {}", finalScore);
            interviewDto.setVideoScore(finalScore);
            interviewDto.setItvDate(new Date());
            log.info("controller memberDot{}", interviewDto);
            return new ResponseEntity<>(interviewService.updateVideoScore(interviewDto), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while update TotalVideo request", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    private double calculateTotalScore(Map<String, Double> emotionAverages, Map<String, List<Double>> posEyeAnalysis) {
        // 감정 평균의 총합 계산
        double emotionSum = emotionAverages.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        // 눈 분석 평균의 총합 계산
        double posEyeSum = posEyeAnalysis.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.averagingDouble(Double::doubleValue));

        // 감정 평균과 눈 분석 평균의 평균 계산
        double totalScore = (emotionSum + posEyeSum) / (emotionAverages.size() + posEyeAnalysis.size());

        // 소수점 2째자리까지 반올림
        return Math.round(totalScore * 100.0) / 100.0;
    }



}
