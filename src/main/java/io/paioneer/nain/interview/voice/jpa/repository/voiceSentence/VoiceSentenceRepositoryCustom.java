package io.paioneer.nain.interview.voice.jpa.repository.voiceSentence;

import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;

import java.util.ArrayList;

public interface VoiceSentenceRepositoryCustom {
    long getPositiveCount(Long voiceNo);

    long getNegativeCount(Long voiceNo);

    ArrayList<VoiceSentenceEntity> getAnswers(Long voiceNo);
}
