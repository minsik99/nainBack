package io.paioneer.nain.resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExperienceEntity is a Querydsl query type for ExperienceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExperienceEntity extends EntityPathBase<ExperienceEntity> {

    private static final long serialVersionUID = 660507087L;

    public static final QExperienceEntity experienceEntity = new QExperienceEntity("experienceEntity");

    public final StringPath company = createString("company");

    public final StringPath current = createString("current");

    public final StringPath department = createString("department");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath exDuration = createString("exDuration");

    public final NumberPath<Long> experienceNo = createNumber("experienceNo", Long.class);

    public final StringPath exPosition = createString("exPosition");

    public final StringPath responsibilities = createString("responsibilities");

    public final NumberPath<Long> resumeNo = createNumber("resumeNo", Long.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QExperienceEntity(String variable) {
        super(ExperienceEntity.class, forVariable(variable));
    }

    public QExperienceEntity(Path<? extends ExperienceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExperienceEntity(PathMetadata metadata) {
        super(ExperienceEntity.class, metadata);
    }

}

