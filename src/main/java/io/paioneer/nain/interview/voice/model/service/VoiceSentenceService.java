package io.paioneer.nain.interview.voice.model.service;

import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;
import io.paioneer.nain.interview.voice.jpa.repository.voiceSentence.VoiceSentenceRepository;
import io.paioneer.nain.interview.voice.model.dto.VoiceSentenceDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VoiceSentenceService {

    private final VoiceSentenceRepository voiceSentenceRepository;

    //답변번호 -> 답변 문장 목록
    public ArrayList<VoiceSentenceDto> getAnswers(Long voiceNo) {
        ArrayList<VoiceSentenceEntity> entities = voiceSentenceRepository.getAnswers(voiceNo);
        ArrayList<VoiceSentenceDto> list = new ArrayList<>();
        for(VoiceSentenceEntity entity : entities) {
            list.add(entity.toDto());
        }
        return list;
    }

    //문장 조회
    public VoiceSentenceDto getAnalysis(Long vsNo) {
        VoiceSentenceDto voiceSentenceDto = voiceSentenceRepository.findById(vsNo).get().toDto();
        return voiceSentenceDto;
    }

    //답변번호 -> 긍정 개수
    public int getPositiveCount(Long voiceNo) {
        int count = (int) voiceSentenceRepository.getPositiveCount(voiceNo);
        return count;
    }

    // 답변번호 -> 부정 개수
    public int getNegativeCount(Long voiceNo) {
        int count = (int) voiceSentenceRepository.getNegativeCount(voiceNo);
        return count;
    }
}
