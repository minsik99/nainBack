package io.paioneer.nain.interview.video.jpa.repository.video;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.interview.video.jpa.entity.EmotionEntity;
import io.paioneer.nain.interview.video.jpa.entity.QEmotionEntity;
import io.paioneer.nain.interview.video.jpa.entity.QVideoEntity;
import io.paioneer.nain.interview.video.jpa.entity.VideoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class VideoRepositoryImpl implements VideoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<VideoEntity> findAllItvNo(Long itvNo) {
        QVideoEntity videoEntity = QVideoEntity.videoEntity;
        return queryFactory.selectFrom(videoEntity)
                .where(videoEntity.interviewEntity.itvNo.eq(itvNo))
                .fetch();
    }

    @Override
    public List<Tuple> findVideoScoresByItvNo(Long itvNo) {
        QVideoEntity videoEntity = QVideoEntity.videoEntity;
        return queryFactory
                .select(videoEntity.answerNo.as("question_no"), videoEntity.videoScore)
                .from(videoEntity)
                .where(videoEntity.interviewEntity.itvNo.eq(itvNo))
                .fetch();
    }
}
