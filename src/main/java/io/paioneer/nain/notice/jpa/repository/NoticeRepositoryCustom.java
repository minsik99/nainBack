package io.paioneer.nain.notice.jpa.repository;

import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface NoticeRepositoryCustom {

    ArrayList<NoticeEntity> findBySearchNoticeContent(String keyword, Pageable pageable);

    ArrayList<NoticeEntity> findBySearchNoticeWriter(String keyword, Pageable pageable);

    ArrayList<NoticeEntity> findBySearchNoticeTitle(String keyword, Pageable pageable);

    Long countByNoticeTitle(String keyword);

    Long countByNoticeContent(String keyword);

    Long countByNoticeWriter(String keyword, Pageable pageable);


}

