package io.paioneer.nain.notice.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeEntity is a Querydsl query type for NoticeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeEntity extends EntityPathBase<NoticeEntity> {

    private static final long serialVersionUID = 1920596840L;

    public static final QNoticeEntity noticeEntity = new QNoticeEntity("noticeEntity");

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    public final StringPath noticeContent = createString("noticeContent");

    public final DateTimePath<java.util.Date> noticeDate = createDateTime("noticeDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> noticeImportent = createDateTime("noticeImportent", java.util.Date.class);

    public final NumberPath<Integer> noticeNo = createNumber("noticeNo", Integer.class);

    public final NumberPath<Integer> noticeReadCount = createNumber("noticeReadCount", Integer.class);

    public final StringPath noticeTitle = createString("noticeTitle");

    public QNoticeEntity(String variable) {
        super(NoticeEntity.class, forVariable(variable));
    }

    public QNoticeEntity(Path<? extends NoticeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeEntity(PathMetadata metadata) {
        super(NoticeEntity.class, metadata);
    }

}

