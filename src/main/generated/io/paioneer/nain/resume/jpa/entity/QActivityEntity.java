package io.paioneer.nain.resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivityEntity is a Querydsl query type for ActivityEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActivityEntity extends EntityPathBase<ActivityEntity> {

    private static final long serialVersionUID = 926557236L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivityEntity activityEntity = new QActivityEntity("activityEntity");

    public final StringPath activityDescription = createString("activityDescription");

    public final StringPath activityName = createString("activityName");

    public final NumberPath<Long> activityNo = createNumber("activityNo", Long.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath organizer = createString("organizer");

    public final QResumeEntity resumeEntity;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QActivityEntity(String variable) {
        this(ActivityEntity.class, forVariable(variable), INITS);
    }

    public QActivityEntity(Path<? extends ActivityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActivityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActivityEntity(PathMetadata metadata, PathInits inits) {
        this(ActivityEntity.class, metadata, inits);
    }

    public QActivityEntity(Class<? extends ActivityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resumeEntity = inits.isInitialized("resumeEntity") ? new QResumeEntity(forProperty("resumeEntity"), inits.get("resumeEntity")) : null;
    }

}

