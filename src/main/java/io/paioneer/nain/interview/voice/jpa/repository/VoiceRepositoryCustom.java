package io.paioneer.nain.interview.voice.jpa.repository;

import io.paioneer.nain.interview.jpa.entity.QuestionEntity;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceEntity;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;
import io.paioneer.nain.interview.voice.model.dto.VoiceDto;

import java.util.ArrayList;
import java.util.List;

public interface VoiceRepositoryCustom {
    ArrayList<VoiceEntity> selectRecord(Long itvNo);

    List<VoiceSentenceEntity> selectAnswer(Long voiceNo);
}
