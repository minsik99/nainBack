package io.paioneer.nain.notice.jpa.repository;

import com.querydsl.core.types.OrderSpecifier;
import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface NoticeRepositoryCustom {

    ArrayList<NoticeEntity> findBySearchNoticeContent(String keyword, Pageable pageable, OrderSpecifier entityPath);

    ArrayList<NoticeEntity> findBySearchNoticeWriter(String keyword, Pageable pageable, OrderSpecifier entityPath);

    ArrayList<NoticeEntity> findBySearchNoticeTitle(String keyword, Pageable pageable, OrderSpecifier entityPath);

    Page<NoticeEntity> findByNoticeDeleteIsNull(Pageable pageable);

    long countByNoticeTitle(String keyword, Pageable pageable);

    long countByNoticeContent(String keyword, Pageable pageable);

    long countByNoticeWriter(String keyword, Pageable pageable);

    //삭제글 갯수 조회
    long countByNoticeDeleteIsNull();

    Long findLastNo();


}

