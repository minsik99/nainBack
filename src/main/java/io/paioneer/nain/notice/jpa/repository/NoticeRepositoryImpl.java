package io.paioneer.nain.notice.jpa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
import io.paioneer.nain.notice.jpa.entity.QNoticeEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManger;

    private QNoticeEntity notice = QNoticeEntity.noticeEntity;


    //작성자 검색
    public ArrayList<NoticeEntity> findBySearchNoticeWriter(String keyword, Pageable pageable) {
        return (ArrayList<NoticeEntity>) queryFactory
                .selectFrom(notice) //selelct * from notice
                .where(notice.memberEntity.memberNickName.like("%" + keyword + "%"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //내용 검색
    @Override
    public ArrayList<NoticeEntity> findBySearchNoticeContent(String keyword, Pageable pageable) {
        return (ArrayList<NoticeEntity>) queryFactory
                .selectFrom(notice) //selelct * from notice
                .where(notice.noticeContent.like("%" + keyword + "%"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //제목 검색
    @Override
    public ArrayList<NoticeEntity> findBySearchNoticeTitle(String keyword, Pageable pageable) {
        return (ArrayList<NoticeEntity>) queryFactory
                .selectFrom(notice) //selelct * from notice
                .where(notice.noticeTitle.like("%" + keyword + "%"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //제목 검색 목록 갯수 조회용
    @Override
    public Long countByNoticeTitle(String keyword) {
        return queryFactory
                .selectFrom(notice) //selelct * from notice
                .where(notice.noticeTitle.like("%" + keyword + "%"))
                .fetchCount();
    }

    //내용 검색 목록 갯수 조회용
    @Override
    public Long countByNoticeContent(String keyword) {
        return queryFactory
                .selectFrom(notice) //selelct * from notice
                .where(notice.noticeContent.like("%" + keyword + "%"))
                .fetchCount();
    }

    //작성자 검색 목록 갯수 조회용
    @Override
    public Long countByNoticeWriter(String keyword, Pageable pageable) {
        return queryFactory
                .select(notice.count())
                .from(notice)
                .where(notice.memberEntity.memberNickName.like("%" + keyword + "%")
                        .and(notice.noticeDelete.isNull()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();
    }

}
