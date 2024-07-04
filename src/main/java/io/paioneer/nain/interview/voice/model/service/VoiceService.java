package io.paioneer.nain.interview.voice.model.service;

import io.paioneer.nain.interview.jpa.entity.QuestionEntity;
import io.paioneer.nain.interview.model.dto.QuestionDto;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceEntity;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;
import io.paioneer.nain.interview.voice.jpa.repository.VoiceRepository;
import io.paioneer.nain.interview.voice.model.dto.VoiceDto;
import io.paioneer.nain.interview.voice.model.dto.VoiceSentenceDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VoiceService {

    private final VoiceRepository voiceRepository;
    private final VoiceSentenceDto voiceSentenceDto;

    public ArrayList<VoiceDto> selectRecord(Long itvNo) {
        ArrayList<VoiceEntity> questions = voiceRepository.selectRecord(itvNo);
        ArrayList<VoiceDto> list = new ArrayList<>();
        for (VoiceEntity question : questions) {
            list.add(question.toDto());
        }
        return list;
    }

    public ArrayList<VoiceSentenceDto> selectAnswer(Long voiceNo) {
        List<VoiceSentenceEntity> answers = voiceRepository.selectAnswer(voiceNo);
        ArrayList<VoiceSentenceDto> list = new ArrayList<>();
        for (VoiceSentenceEntity voiceSentence : answers) {
            VoiceSentenceDto voiceSentenceDto = voiceSentence.toDto();
            float positive = (float) Math.round(voiceSentenceDto.getPositive() * 1000) / 10;
            float negative = (float) Math.round(voiceSentenceDto.getNegative() * 1000) / 10;
            voiceSentenceDto.setPositive(positive);
            voiceSentenceDto.setNegative(negative);
            list.add(voiceSentenceDto);
        }
        return list;
    }
}
