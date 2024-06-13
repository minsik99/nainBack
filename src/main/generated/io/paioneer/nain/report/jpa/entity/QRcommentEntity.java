package io.paioneer.nain.report.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRcommentEntity is a Querydsl query type for RcommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRcommentEntity extends EntityPathBase<RcommentEntity> {

    private static final long serialVersionUID = 445771417L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRcommentEntity rcommentEntity = new QRcommentEntity("rcommentEntity");

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity adminEntity;

    public final io.paioneer.nain.community.jpa.entity.QCommentEntity commentEntity;

    public final NumberPath<Long> cReportNo = createNumber("cReportNo", Long.class);

    public final DateTimePath<java.util.Date> handledDate = createDateTime("handledDate", java.util.Date.class);

    public final StringPath handledYN = createString("handledYN");

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity memberEntity;

    public final DateTimePath<java.util.Date> reportDate = createDateTime("reportDate", java.util.Date.class);

    public final StringPath reportType = createString("reportType");

    public QRcommentEntity(String variable) {
        this(RcommentEntity.class, forVariable(variable), INITS);
    }

    public QRcommentEntity(Path<? extends RcommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRcommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRcommentEntity(PathMetadata metadata, PathInits inits) {
        this(RcommentEntity.class, metadata, inits);
    }

    public QRcommentEntity(Class<? extends RcommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.adminEntity = inits.isInitialized("adminEntity") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("adminEntity")) : null;
        this.commentEntity = inits.isInitialized("commentEntity") ? new io.paioneer.nain.community.jpa.entity.QCommentEntity(forProperty("commentEntity"), inits.get("commentEntity")) : null;
        this.memberEntity = inits.isInitialized("memberEntity") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("memberEntity")) : null;
    }

}

