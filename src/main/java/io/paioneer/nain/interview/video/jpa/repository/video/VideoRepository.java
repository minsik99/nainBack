package io.paioneer.nain.interview.video.jpa.repository.video;


import io.paioneer.nain.interview.video.jpa.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> , VideoRepositoryCustom {

}
