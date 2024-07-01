package io.paioneer.nain.interview.voice.jpa.repository;

import io.paioneer.nain.interview.voice.jpa.entity.VoiceEntity;
import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;
import io.paioneer.nain.interview.voice.model.dto.VoiceSentenceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoiceRepository extends JpaRepository<VoiceEntity, Long>, VoiceRepositoryCustom {
}
