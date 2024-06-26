package io.paioneer.nain.interview.voice.jpa.repository.voice;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.interview.voice.jpa.entity.QVoiceEntity;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Slf4j
@Repository
@RequiredArgsConstructor
public class VoiceRepositoryImpl implements VoiceRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private QVoiceEntity voiceEntity = QVoiceEntity.voiceEntity;

    @Override
    public long getAnswerCount(Long itvNo) {
        return queryFactory
                .select(voiceEntity.count())
                .from(voiceEntity)
                .where(voiceEntity.interviewEntity.itvNo.eq(itvNo))
                .fetchOne();
    }

    @Override
    public ArrayList<VoiceEntity> getParagraph(Long itvNo) {
        return (ArrayList<VoiceEntity>) queryFactory
                .selectFrom(voiceEntity)
                .where(voiceEntity.interviewEntity.itvNo.eq(itvNo))
                .fetch();
    }
}
