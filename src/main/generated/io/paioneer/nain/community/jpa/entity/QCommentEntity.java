package io.paioneer.nain.community.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentEntity is a Querydsl query type for CommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentEntity extends EntityPathBase<CommentEntity> {

    private static final long serialVersionUID = -212157638L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentEntity commentEntity1 = new QCommentEntity("commentEntity1");

    public final DateTimePath<java.util.Date> commentDate = createDateTime("commentDate", java.util.Date.class);

    public final QCommentEntity commentEntity;

    public final NumberPath<Long> commentNo = createNumber("commentNo", Long.class);

    public final QCommunityEntity communityEntity;

    public final StringPath content = createString("content");

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity memberEntity;

    public QCommentEntity(String variable) {
        this(CommentEntity.class, forVariable(variable), INITS);
    }

    public QCommentEntity(Path<? extends CommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentEntity(PathMetadata metadata, PathInits inits) {
        this(CommentEntity.class, metadata, inits);
    }

    public QCommentEntity(Class<? extends CommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commentEntity = inits.isInitialized("commentEntity") ? new QCommentEntity(forProperty("commentEntity"), inits.get("commentEntity")) : null;
        this.communityEntity = inits.isInitialized("communityEntity") ? new QCommunityEntity(forProperty("communityEntity"), inits.get("communityEntity")) : null;
        this.memberEntity = inits.isInitialized("memberEntity") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("memberEntity")) : null;
    }

}

