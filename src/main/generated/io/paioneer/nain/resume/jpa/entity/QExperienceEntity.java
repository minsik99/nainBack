package io.paioneer.nain.resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExperienceEntity is a Querydsl query type for ExperienceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperienceEntity extends EntityPathBase<ExperienceEntity> {

    private static final long serialVersionUID = 660507087L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExperienceEntity experienceEntity = new QExperienceEntity("experienceEntity");

    public final StringPath comName = createString("comName");

    public final StringPath department = createString("department");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath exCurrent = createString("exCurrent");

    public final NumberPath<Long> exDuration = createNumber("exDuration", Long.class);

    public final NumberPath<Long> experienceNo = createNumber("experienceNo", Long.class);

    public final StringPath exPosition = createString("exPosition");

    public final StringPath responsibilities = createString("responsibilities");

    public final QResumeEntity resumeEntity;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QExperienceEntity(String variable) {
        this(ExperienceEntity.class, forVariable(variable), INITS);
    }

    public QExperienceEntity(Path<? extends ExperienceEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExperienceEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExperienceEntity(PathMetadata metadata, PathInits inits) {
        this(ExperienceEntity.class, metadata, inits);
    }

    public QExperienceEntity(Class<? extends ExperienceEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resumeEntity = inits.isInitialized("resumeEntity") ? new QResumeEntity(forProperty("resumeEntity"), inits.get("resumeEntity")) : null;
    }

}

