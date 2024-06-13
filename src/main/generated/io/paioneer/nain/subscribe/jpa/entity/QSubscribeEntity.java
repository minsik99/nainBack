package io.paioneer.nain.subscribe.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubscribeEntity is a Querydsl query type for SubscribeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubscribeEntity extends EntityPathBase<SubscribeEntity> {

    private static final long serialVersionUID = 1207428580L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubscribeEntity subscribeEntity = new QSubscribeEntity("subscribeEntity");

    public final DateTimePath<java.util.Date> expiryDate = createDateTime("expiryDate", java.util.Date.class);

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity memberEntity;

    public final NumberPath<Integer> payAmount = createNumber("payAmount", Integer.class);

    public final DateTimePath<java.util.Date> paymentDate = createDateTime("paymentDate", java.util.Date.class);

    public final NumberPath<Integer> payNo = createNumber("payNo", Integer.class);

    public QSubscribeEntity(String variable) {
        this(SubscribeEntity.class, forVariable(variable), INITS);
    }

    public QSubscribeEntity(Path<? extends SubscribeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubscribeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubscribeEntity(PathMetadata metadata, PathInits inits) {
        this(SubscribeEntity.class, metadata, inits);
    }

    public QSubscribeEntity(Class<? extends SubscribeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("memberEntity")) : null;
    }

}

