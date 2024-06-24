package io.paioneer.nain.resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResumeEntity is a Querydsl query type for ResumeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResumeEntity extends EntityPathBase<ResumeEntity> {

    private static final long serialVersionUID = 85318482L;

    public static final QResumeEntity resumeEntity = new QResumeEntity("resumeEntity");

    public final StringPath bookMarked = createString("bookMarked");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> deleteDate = createDateTime("deleteDate", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath introduction = createString("introduction");

    public final StringPath jobCategory = createString("jobCategory");

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    public final DateTimePath<java.util.Date> modificationDate = createDateTime("modificationDate", java.util.Date.class);

    public final StringPath phone = createString("phone");

    public final StringPath resumeName = createString("resumeName");

    public final NumberPath<Long> resumeNo = createNumber("resumeNo", Long.class);

    public final StringPath title = createString("title");

    public QResumeEntity(String variable) {
        super(ResumeEntity.class, forVariable(variable));
    }

    public QResumeEntity(Path<? extends ResumeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResumeEntity(PathMetadata metadata) {
        super(ResumeEntity.class, metadata);
    }

}

