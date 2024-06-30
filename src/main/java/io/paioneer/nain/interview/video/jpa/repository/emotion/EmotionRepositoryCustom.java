package io.paioneer.nain.interview.video.jpa.repository.emotion;


import com.querydsl.core.Tuple;
import io.paioneer.nain.interview.video.jpa.entity.EmotionEntity;

import java.util.List;

public interface EmotionRepositoryCustom {

    List<EmotionEntity> findAllItvNo(Long itvNo);

    List<Tuple> findEmotionScoresByItvNo(Long itvNo);
}
