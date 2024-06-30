package io.paioneer.nain.interview.video.jpa.repository.emotion;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.interview.video.jpa.entity.EmotionEntity;
import io.paioneer.nain.interview.video.jpa.entity.QEmotionEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmotionRepositoryImpl implements EmotionRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    QEmotionEntity emotionEntity = QEmotionEntity.emotionEntity;
    @Override
    public List<EmotionEntity> findAllItvNo(Long itvNo) {
        return queryFactory.selectFrom(emotionEntity)
                .where(emotionEntity.interviewEntity.itvNo.eq(itvNo))
                .fetch();
    }

    @Override
    public List<Tuple> findEmotionScoresByItvNo(Long itvNo) {
        return queryFactory
                .select(emotionEntity.answerEno.as("question_no"), emotionEntity.eresultData)
                .from(emotionEntity)
                .where(emotionEntity.interviewEntity.itvNo.eq(itvNo))
                .fetch();
    }


}
