package io.paioneer.nain.interview.video.model.service;


import io.paioneer.nain.interview.video.jpa.entity.EmotionEntity;
import io.paioneer.nain.interview.video.jpa.entity.VideoEntity;
import io.paioneer.nain.interview.video.jpa.repository.emotion.EmotionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmotionService {
    private final EmotionRepository emotionRepository;

    public Map<String, Double> calculateEmotionAverages(Long itvNo) {
        List<EmotionEntity> emotions = emotionRepository.findAllItvNo(itvNo);
        return emotions.stream()
                .collect(Collectors.groupingBy(
                        EmotionEntity::getEmotionName,
                        Collectors.collectingAndThen(
                                Collectors.averagingDouble(EmotionEntity::getEresultData),
                                avg -> Math.round(avg * 100.0) / 100.0
                        )
                ));
    }


}
