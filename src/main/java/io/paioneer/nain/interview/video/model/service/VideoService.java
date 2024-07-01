package io.paioneer.nain.interview.video.model.service;

import com.querydsl.core.Tuple;
import io.paioneer.nain.interview.video.jpa.entity.EmotionEntity;
import io.paioneer.nain.interview.video.jpa.entity.VideoEntity;
import io.paioneer.nain.interview.video.jpa.repository.emotion.EmotionRepository;
import io.paioneer.nain.interview.video.jpa.repository.video.VideoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final EmotionRepository emotionRepository;

    public Map<String, List<Double>> selectListPosEyeAnaly(Long itvNo) {
        List<VideoEntity> videoEntities = videoRepository.findAllItvNo(itvNo);
        Map<String, List<Double>> result = new HashMap<>();

        List<Double> posScores = videoEntities.stream()
                .filter(video -> "POS".equals(video.getItvType()))
                .map(VideoEntity::getVideoScore)
                .collect(Collectors.toList());

        List<Double> eyeScores = videoEntities.stream()
                .filter(video -> "EYE".equals(video.getItvType()))
                .map(VideoEntity::getVideoScore)
                .collect(Collectors.toList());

        // 결과 맵에 저장
        result.put("POS", posScores);
        result.put("EYE", eyeScores);

        return result;

    }

    public Map<Integer, Double> getAverageScores(Long itvNo) {
        try {
            // Repository 호출 부분
            List<Tuple> videoResults = videoRepository.findVideoScoresByItvNo(itvNo);
            List<Tuple> emotionResults = emotionRepository.findEmotionScoresByItvNo(itvNo);

            // Combined results 초기화
            Map<Integer, List<Double>> combinedResults = new HashMap<>();

            // Video 결과 처리
            for (Tuple tuple : videoResults) {
                Integer key = tuple.get(0, Integer.class); // Long 타입으로 추출
                Double value = tuple.get(1, Double.class);
                combinedResults.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            }


            // Emotion 결과 처리
            for (Tuple tuple : emotionResults) {
                Integer key = tuple.get(0, Integer.class); // Long 타입으로 추출
                Double value = tuple.get(1, Double.class);
                combinedResults.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            }

            // 평균 점수 계산
            Map<Integer, Double> avgScores = new HashMap<>();
            combinedResults.forEach((k, v) -> {
                double avg = v.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                BigDecimal roundedAvg = BigDecimal.valueOf(avg).setScale(2, RoundingMode.HALF_UP); // 소수점 둘째자리까지 반올림
                avgScores.put(k, roundedAvg.doubleValue());
            });

            log.info("avgScores {} ::::::::::", avgScores.toString());
            return avgScores;
        } catch (Exception e) {
            log.error("Error while calculating average scores", e);
            throw e;  // 필요에 따라 예외를 다시 던져 상위 메서드에서 처리
        }
    }
}
