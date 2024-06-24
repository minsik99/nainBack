package io.paioneer.nain.interview.voice.jpa.repository.voiceSentence;

import io.paioneer.nain.interview.voice.jpa.entity.VoiceSentenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceSentenceRepository extends JpaRepository<VoiceSentenceEntity, Long>, VoiceSentenceRepositoryCustom {
}
