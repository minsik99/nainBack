package io.paioneer.nain.interview.voice.jpa.repository.voice;

import io.paioneer.nain.interview.voice.jpa.entity.VoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoiceRepository extends JpaRepository<VoiceEntity, Long>, VoiceRepositoryCustom {
}
