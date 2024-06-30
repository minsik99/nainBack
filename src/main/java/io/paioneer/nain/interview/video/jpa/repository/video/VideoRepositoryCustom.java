package io.paioneer.nain.interview.video.jpa.repository.video;


import com.querydsl.core.Tuple;
import io.paioneer.nain.interview.video.jpa.entity.VideoEntity;

import java.util.List;

public interface VideoRepositoryCustom {

    List<VideoEntity> findAllItvNo(Long itvNo);

    List<Tuple> findVideoScoresByItvNo(Long itvNo);
}
