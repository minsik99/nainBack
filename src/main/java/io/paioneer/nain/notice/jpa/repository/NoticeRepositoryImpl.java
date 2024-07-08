package io.paioneer.nain.notice.jpa.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.notice.jpa.entity.NoticeEntity;
import io.paioneer.nain.notice.jpa.entity.QNoticeEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManger;

    private QNoticeEntity notice = QNoticeEntity.noticeEntity;


    //작성자 검색
    public ArrayList<NoticeEntity> findBySearchNoticeWriter(String keyword, Pageable pageable, OrderSpecifier entityPath) {
        return (ArrayList<NoticeEntity>) queryFactory
                .selectFrom(notice) //selelct * from notice
                .where(notice.memberEntity.memberNickName.lower().like("%" + keyword.toLowerCase() + "%")
                .and(notice.noticeDelete.isNull()))
                .orderBy(notice.noticeImportent.desc().nullsLast(), entityPath)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //내용 검색
    @Override
    public ArrayList<NoticeEntity> findBySearchNoticeContent(String keyword, Pageable pageable, OrderSpecifier entityPath) {
        return (ArrayList<NoticeEntity>) queryFactory
                .selectFrom(notice) //selelct * from notice
                .where(notice.noticeContent.lower().like("%" + keyword.toLowerCase() + "%")
                .and(notice.noticeDelete.isNull()))
                .orderBy(notice.noticeImportent.desc().nullsLast(), entityPath)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //제목 검색
    @Override
    public ArrayList<NoticeEntity> findBySearchNoticeTitle(String keyword,
              Pageable pageable, OrderSpecifier entityPath) {

        return (ArrayList<NoticeEntity>) queryFactory
                .selectFrom(notice) //selelct * from notice
                .where(notice.noticeTitle.lower().like("%" + keyword.toLowerCase() + "%")
                .and(notice.noticeDelete.isNull()))
                .orderBy(notice.noticeImportent.desc().nullsLast(), entityPath)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


    //제목 검색 목록 갯수 조회용
    @Override
    public long countByNoticeTitle(String keyword, Pageable pageable) {
        return queryFactory
                .select(notice.count())
                .from(notice)
                .where(notice.noticeTitle.like("%" + keyword + "%")
                        .and(notice.noticeDelete.isNull()))
                .fetchOne();
    }

    //내용 검색 목록 갯수 조회용
    @Override
    public long countByNoticeContent(String keyword, Pageable pageable) {
        return queryFactory
                .select(notice.count())
                .from(notice)
                .where(notice.noticeContent.like("%" + keyword + "%")
                        .and(notice.noticeDelete.isNull()))
                .fetchOne();
    }

    //작성자 검색 목록 갯수 조회용
    @Override
    public long countByNoticeWriter(String keyword, Pageable pageable) {
        return queryFactory
                .select(notice.count())
                .from(notice)
                .where(notice.memberEntity.memberNickName.like("%" + keyword + "%")
                        .and(notice.noticeDelete.isNull()))
                .fetchOne();
    }

    //삭제값 처리
    @Override
    public Page<NoticeEntity> findByNoticeDeleteIsNull(Pageable pageable) {
        List<NoticeEntity> entities = queryFactory
                .selectFrom(notice)
                .where(notice.noticeDelete.isNull())
                .orderBy(notice.noticeNo.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = queryFactory
                .selectFrom(notice)
                .where(notice.noticeDelete.isNull())
                .fetchCount();

        return new PageImpl<>(entities, pageable, count);
    }

    //삭제글 갯수 조회
    @Override
    public long countByNoticeDeleteIsNull() {
        return queryFactory
                .selectFrom(notice)
                .where(notice.noticeDelete.isNull())
                .fetchCount();
    }
    
    //마지막 번호 찾기
    @Override
    public Long findLastNo() {
        return queryFactory
                .select(notice.noticeNo.max())
                .from(notice)
                .fetchOne();
    }
}
