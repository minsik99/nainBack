package io.paioneer.nain.interview.voice.jpa.repository.voiceSentence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.interview.voice.jpa.entity.QVoiceSentenceEntity;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Slf4j
@Repository
@RequiredArgsConstructor
public class VoiceSentenceRepositoryImpl implements VoiceSentenceRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private QVoiceSentenceEntity voiceSentenceEntity = QVoiceSentenceEntity.voiceSentenceEntity;

    @Override
    public long getPositiveCount(Long voiceNo) {
        return queryFactory
                .select(voiceSentenceEntity.count())
                .from(voiceSentenceEntity)
                .where(voiceSentenceEntity.voiceEntity.voiceNo.eq(voiceNo)
                        .and(voiceSentenceEntity.positive.goe(60)))
                .fetchOne();
    }

    @Override
    public long getNegativeCount(Long voiceNo) {
        return queryFactory
                .select(voiceSentenceEntity.count())
                .from(voiceSentenceEntity)
                .where(voiceSentenceEntity.voiceEntity.voiceNo.eq(voiceNo)
                        .and(voiceSentenceEntity.negative.goe(60)))
                .fetchOne();
    }

    @Override
    public ArrayList<VoiceSentenceEntity> getAnswers(Long voiceNo) {
        return (ArrayList<VoiceSentenceEntity>) queryFactory
                .selectFrom(voiceSentenceEntity)
                .where(voiceSentenceEntity.voiceEntity.voiceNo.eq(voiceNo))
                .fetch();
    }
}
