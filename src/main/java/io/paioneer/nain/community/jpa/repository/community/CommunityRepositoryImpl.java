package io.paioneer.nain.community.jpa.repository.community;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import io.paioneer.nain.community.jpa.entity.QCommunityEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CommunityRepositoryImpl implements CommunityRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private  QCommunityEntity communityEntity = QCommunityEntity.communityEntity;

    // 내 글 목록
    @Override
    public long countMyList(Long memberNo, OrderSpecifier entityPath) {
        return queryFactory
                .select(communityEntity.count())
                .from(communityEntity)
                .where(communityEntity.memberEntity.memberNo.eq(memberNo)
                        .and(communityEntity.deletedDate.isNull()))
                .orderBy(entityPath)
                .fetchOne();
    }

    //제목 검색 개수
    @Override
    public long searchTitleCount(String keyword, Pageable pageable) {
            Long count = 0L;
            if (keyword == null || keyword.isEmpty()){
                log.info("여기서 작동");
               count =  queryFactory
                            .select(communityEntity.count())
                            .from(communityEntity)
                            .where(communityEntity.deletedDate.isNull())
                            .fetchOne();
            }else{
                count =  queryFactory
                        .select(communityEntity.count())
                        .from(communityEntity)
                        .where(communityEntity.title.like("%" + keyword + "%")
                                .and(communityEntity.deletedDate.isNull()))
                        .fetchOne();
            }
            log.info("페이징 값 입력 후 listCount: {}", count);
        return count != null ? count : 0;
    }

    //작성자 검색 개수
    @Override
    public long searchWriterCount(String keyword, Pageable pageable) {
        return queryFactory
                .select(communityEntity.count())
                .from(communityEntity)
                .where(communityEntity.memberEntity.memberNickName.like("%" + keyword + "%")
                        .and(communityEntity.deletedDate.isNull()))
                .fetchOne();
    }

    //내용 검색 개수
    @Override
    public long searchContentCount(String keyword, Pageable pageable) {
        return queryFactory
                .select(communityEntity.count())
                .from(communityEntity)
                .where(communityEntity.content.like("%" + keyword + "%")
                        .and(communityEntity.deletedDate.isNull()))
                .fetchOne();
    }

    @Override
    public Long findLastNo() {
        return queryFactory
                .select(communityEntity.communityNo.max())
                .from(communityEntity)
                .fetchOne();
    }

    //제목 검색 목록 조회
    @Override
    public ArrayList<CommunityEntity> searchTitle(String keyword, Pageable pageable, OrderSpecifier entityPath) {
        BooleanExpression titleCondition = keyword == null || keyword.trim().isEmpty()
                ? null
                : communityEntity.title.like("%" + keyword.trim() + "%");
        return (ArrayList<CommunityEntity>) queryFactory
                .selectFrom(communityEntity)
                .where(titleCondition, communityEntity.title.lower().like("%" + keyword.toLowerCase() + "%")
                .and(communityEntity.deletedDate.isNull()))
                .orderBy(entityPath)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //작성자 검색 목록 조회
    @Override
    public ArrayList<CommunityEntity> searchWriter(String keyword, Pageable pageable, OrderSpecifier entityPath) {
        return (ArrayList<CommunityEntity>) queryFactory
                .selectFrom(communityEntity)
                .where(communityEntity.memberEntity.memberNickName.lower().like("%" + keyword.toLowerCase() + "%")
                .and(communityEntity.deletedDate.isNull()))
                .orderBy(entityPath)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //내용 검색 목록 조회
    @Override
    public ArrayList<CommunityEntity> searchContent(String keyword, Pageable pageable, OrderSpecifier entityPath) {
        return (ArrayList<CommunityEntity>) queryFactory
                .selectFrom(communityEntity)
                .where(communityEntity.content.lower().like("%" + keyword.toLowerCase() + "%")
                .and(communityEntity.deletedDate.isNull()))
                .orderBy(entityPath)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
