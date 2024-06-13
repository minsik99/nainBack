package io.paioneer.nain.report.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRcommunityEntity is a Querydsl query type for RcommunityEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRcommunityEntity extends EntityPathBase<RcommunityEntity> {

    private static final long serialVersionUID = -1250337053L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRcommunityEntity rcommunityEntity = new QRcommunityEntity("rcommunityEntity");

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity adminEntity;

    public final NumberPath<Long> bReportNo = createNumber("bReportNo", Long.class);

    public final io.paioneer.nain.community.jpa.entity.QCommunityEntity communityEntity;

    public final DateTimePath<java.util.Date> handledDate = createDateTime("handledDate", java.util.Date.class);

    public final StringPath handledYN = createString("handledYN");

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity memberEntity;

    public final DateTimePath<java.util.Date> reportDate = createDateTime("reportDate", java.util.Date.class);

    public final StringPath reportType = createString("reportType");

    public QRcommunityEntity(String variable) {
        this(RcommunityEntity.class, forVariable(variable), INITS);
    }

    public QRcommunityEntity(Path<? extends RcommunityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRcommunityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRcommunityEntity(PathMetadata metadata, PathInits inits) {
        this(RcommunityEntity.class, metadata, inits);
    }

    public QRcommunityEntity(Class<? extends RcommunityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.adminEntity = inits.isInitialized("adminEntity") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("adminEntity")) : null;
        this.communityEntity = inits.isInitialized("communityEntity") ? new io.paioneer.nain.community.jpa.entity.QCommunityEntity(forProperty("communityEntity"), inits.get("communityEntity")) : null;
        this.memberEntity = inits.isInitialized("memberEntity") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("memberEntity")) : null;
    }

}

