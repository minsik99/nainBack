package io.paioneer.nain.resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEducationEntity is a Querydsl query type for EducationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEducationEntity extends EntityPathBase<EducationEntity> {

    private static final long serialVersionUID = -957301367L;

    public static final QEducationEntity educationEntity = new QEducationEntity("educationEntity");

    public final StringPath current = createString("current");

    public final StringPath degree = createString("degree");

    public final NumberPath<Long> educationNo = createNumber("educationNo", Long.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath major = createString("major");

    public final NumberPath<Long> resumeNo = createNumber("resumeNo", Long.class);

    public final StringPath schoolName = createString("schoolName");

    public final NumberPath<Float> score = createNumber("score", Float.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QEducationEntity(String variable) {
        super(EducationEntity.class, forVariable(variable));
    }

    public QEducationEntity(Path<? extends EducationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEducationEntity(PathMetadata metadata) {
        super(EducationEntity.class, metadata);
    }

}

