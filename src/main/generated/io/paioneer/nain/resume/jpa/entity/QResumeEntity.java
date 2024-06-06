package io.paioneer.nain.resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResumeEntity is a Querydsl query type for ResumeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResumeEntity extends EntityPathBase<ResumeEntity> {

    private static final long serialVersionUID = 85318482L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResumeEntity resumeEntity = new QResumeEntity("resumeEntity");

    public final StringPath bookMarked = createString("bookMarked");

    public final StringPath email = createString("email");

    public final StringPath jobCategory = createString("jobCategory");

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity memberEntity;

    public final DateTimePath<java.util.Date> modificationDate = createDateTime("modificationDate", java.util.Date.class);

    public final StringPath phone = createString("phone");

    public final StringPath resumeName = createString("resumeName");

    public final NumberPath<Long> resumeNo = createNumber("resumeNo", Long.class);

    public final StringPath title = createString("title");

    public QResumeEntity(String variable) {
        this(ResumeEntity.class, forVariable(variable), INITS);
    }

    public QResumeEntity(Path<? extends ResumeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResumeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResumeEntity(PathMetadata metadata, PathInits inits) {
        this(ResumeEntity.class, metadata, inits);
    }

    public QResumeEntity(Class<? extends ResumeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("memberEntity")) : null;
    }

}

