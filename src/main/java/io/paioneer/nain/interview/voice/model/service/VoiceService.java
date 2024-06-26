package io.paioneer.nain.interview.voice.model.service;

import io.paioneer.nain.interview.voice.jpa.entity.VoiceEntity;
import io.paioneer.nain.interview.voice.jpa.repository.voice.VoiceRepository;
import io.paioneer.nain.interview.voice.model.dto.VoiceDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VoiceService {

    private final VoiceRepository voiceRepository;

    //면접 번호 -> 답변 개수
    public int getAnswerCount(Long itvNo) {
        int count = (int) voiceRepository.getAnswerCount(itvNo);
        return  count;
    }

    //면접 번호 -> 답변 번호
    public ArrayList getParagraph(Long itvNo) {
        ArrayList<VoiceEntity> entities = voiceRepository.getParagraph(itvNo);
        ArrayList list = new ArrayList();
        for(VoiceEntity voiceEntity : entities){
            list.add(voiceEntity.toDto());
        }
        return list;
    }

    //답변 번호 -> 답변
    public VoiceDto getVoiceDto(Long voiceNo) {
        VoiceDto voiceDto = voiceRepository.findById(voiceNo).get().toDto();
        return voiceDto;
    }
}
