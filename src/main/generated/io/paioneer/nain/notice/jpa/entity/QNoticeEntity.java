package io.paioneer.nain.notice.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeEntity is a Querydsl query type for NoticeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeEntity extends EntityPathBase<NoticeEntity> {

    private static final long serialVersionUID = 1920596840L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeEntity noticeEntity = new QNoticeEntity("noticeEntity");

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity memberEntity;

    public final StringPath noticeContent = createString("noticeContent");

    public final DateTimePath<java.util.Date> noticeDate = createDateTime("noticeDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> noticeDelete = createDateTime("noticeDelete", java.util.Date.class);

    public final StringPath noticeFile = createString("noticeFile");

    public final DateTimePath<java.util.Date> noticeImportent = createDateTime("noticeImportent", java.util.Date.class);

    public final StringPath noticeMFile = createString("noticeMFile");

    public final DateTimePath<java.util.Date> noticeModify = createDateTime("noticeModify", java.util.Date.class);

    public final NumberPath<Long> noticeNo = createNumber("noticeNo", Long.class);

    public final NumberPath<Long> noticeReadCount = createNumber("noticeReadCount", Long.class);

    public final StringPath noticeTitle = createString("noticeTitle");

    public QNoticeEntity(String variable) {
        this(NoticeEntity.class, forVariable(variable), INITS);
    }

    public QNoticeEntity(Path<? extends NoticeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoticeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoticeEntity(PathMetadata metadata, PathInits inits) {
        this(NoticeEntity.class, metadata, inits);
    }

    public QNoticeEntity(Class<? extends NoticeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("memberEntity")) : null;
    }

}

