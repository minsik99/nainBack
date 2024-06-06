package io.paioneer.nain.resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEducationEntity is a Querydsl query type for EducationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEducationEntity extends EntityPathBase<EducationEntity> {

    private static final long serialVersionUID = -957301367L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEducationEntity educationEntity = new QEducationEntity("educationEntity");

    public final StringPath educationCurrent = createString("educationCurrent");

    public final StringPath educationDegree = createString("educationDegree");

    public final NumberPath<Long> educationNo = createNumber("educationNo", Long.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath major = createString("major");

    public final QResumeEntity resumeEntity;

    public final NumberPath<Long> score = createNumber("score", Long.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QEducationEntity(String variable) {
        this(EducationEntity.class, forVariable(variable), INITS);
    }

    public QEducationEntity(Path<? extends EducationEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEducationEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEducationEntity(PathMetadata metadata, PathInits inits) {
        this(EducationEntity.class, metadata, inits);
    }

    public QEducationEntity(Class<? extends EducationEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resumeEntity = inits.isInitialized("resumeEntity") ? new QResumeEntity(forProperty("resumeEntity"), inits.get("resumeEntity")) : null;
    }

}

