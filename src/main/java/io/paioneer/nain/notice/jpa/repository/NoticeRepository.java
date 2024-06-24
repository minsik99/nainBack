package io.paioneer.nain.notice.jpa.repository;

import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>, NoticeRepositoryCustom{
    //QueryDSL Repository 사용



}
