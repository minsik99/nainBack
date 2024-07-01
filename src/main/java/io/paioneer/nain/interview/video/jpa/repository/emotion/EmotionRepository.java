package io.paioneer.nain.interview.video.jpa.repository.emotion;


import io.paioneer.nain.interview.video.jpa.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionRepository extends JpaRepository<VideoEntity, Long> , EmotionRepositoryCustom {

}
