package io.paioneer.nain.community.jpa.repository.community;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.paioneer.nain.community.jpa.entity.CommunityEntity;
import io.paioneer.nain.community.jpa.entity.QCommunityEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommunityRepositoryImpl implements CommunityRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private  QCommunityEntity communityEntity = QCommunityEntity.communityEntity;

    //삭제된 글 이외 전체 목록 조회
    @Override
    public Page<CommunityEntity> findListAll(Pageable pageable){
        return  (Page<CommunityEntity>) queryFactory
                .selectFrom(communityEntity)
                .where(communityEntity.deletedDate.eq(null))
                .limit(pageable.getPageSize())
                .fetch();
    }

    //내 글 목록 조회
    @Override
    public Page<CommunityEntity> findMyList(Long memberNo, Pageable pageable) {
        return (Page<CommunityEntity>) queryFactory
                .selectFrom(communityEntity)
                .where(communityEntity.memberEntity.memberNo.eq(memberNo))
                .and(communityEntity.deletedDate.eq(null))
                .limit(pageable.getPageSize())
                .fetch();
    }

    //제목 검색 목록 조회
    @Override
    public Page<CommunityEntity> searchTitle(String keyword, Pageable pageable) {
        return (Page<CommunityEntity>) queryFactory
                .selectFrom(communityEntity)
                .where(communityEntity.title.like("%" + keyword + "%"))
                .and(communityEntity.deletedDate.eq(null))
                .limit(pageable.getPageSize())
                .fetch();
    }

    //작성자 검색 목록 조회
    @Override
    public Page<CommunityEntity> searchWriter(String keyword, Pageable pageable) {
        return (Page<CommunityEntity>) queryFactory
                .selectFrom(communityEntity)
                .where(communityEntity.memberEntity.memberNickName.like("%" + keyword + "%"))
                .and(communityEntity.deletedDate.eq(null))
                .limit(pageable.getPageSize())
                .fetch();
    }

    //내용 검색 목록 조회
    @Override
    public Page<CommunityEntity> searchContent(String keyword, Pageable pageable) {
        return (Page<CommunityEntity>) queryFactory
                .selectFrom(communityEntity)
                .where(communityEntity.content.like("%" + keyword + "%"))
                .and(communityEntity.deletedDate.eq(null))
                .limit(pageable.getPageSize())
                .fetch();
    }
}
