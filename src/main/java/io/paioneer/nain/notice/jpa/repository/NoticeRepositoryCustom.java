package io.paioneer.nain.notice.jpa.repository;

import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface NoticeRepositoryCustom {

    ArrayList<NoticeEntity> findBySearchNoticeContent(String keyword, Pageable pageable);

    ArrayList<NoticeEntity> findBySearchNoticeWriter(String keyword, Pageable pageable);

    ArrayList<NoticeEntity> findBySearchNoticeTitle(String keyword, Pageable pageable);

    long countByNoticeTitle(String keyword);

    long countByNoticeContent(String keyword);

    long countByNoticeWriter(String keyword, Pageable pageable);

    Long findLastNo();


}

