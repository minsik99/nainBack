package io.paioneer.nain.interview.voice.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.interview.jpa.entity.QQuestionEntity;
import io.paioneer.nain.interview.voice.jpa.entity.QVoiceEntity;
import io.paioneer.nain.interview.voice.jpa.entity.QVoiceSentenceEntity;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceEntity;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class VoiceRepositoryImpl implements VoiceRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private QVoiceEntity voiceEntity = QVoiceEntity.voiceEntity;
    private QVoiceSentenceEntity voiceSentenceEntity = QVoiceSentenceEntity.voiceSentenceEntity;
    private QQuestionEntity questionEntity = QQuestionEntity.questionEntity;

    @Override
    public ArrayList<VoiceEntity> selectRecord(Long itvNo) {
        return new ArrayList<>(queryFactory
                .selectFrom(voiceEntity)
                .where(voiceEntity.interviewEntity.itvNo.eq(itvNo))
                .orderBy(voiceEntity.voiceNo.asc())
                .fetch());
    }

    @Override
    public List<VoiceSentenceEntity> selectAnswer(Long voiceNo) {
        return queryFactory
                .selectFrom(voiceSentenceEntity)
                .where(voiceSentenceEntity.voiceEntity.voiceNo.eq(voiceNo))
                .orderBy(voiceSentenceEntity.voiceEntity.voiceNo.asc())
                .fetch();
    }
}
