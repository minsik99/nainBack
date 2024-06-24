package io.paioneer.nain.interview.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterviewEntity is a Querydsl query type for InterviewEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterviewEntity extends EntityPathBase<InterviewEntity> {

    private static final long serialVersionUID = 894940740L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterviewEntity interviewEntity = new QInterviewEntity("interviewEntity");

    public final DateTimePath<java.util.Date> itvDate = createDateTime("itvDate", java.util.Date.class);

    public final NumberPath<Long> itvNo = createNumber("itvNo", Long.class);

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity member;

    public final StringPath title = createString("title");

    public final StringPath url = createString("url");

    public final NumberPath<Integer> videoScore = createNumber("videoScore", Integer.class);

    public final NumberPath<Integer> voiceScore = createNumber("voiceScore", Integer.class);

    public QInterviewEntity(String variable) {
        this(InterviewEntity.class, forVariable(variable), INITS);
    }

    public QInterviewEntity(Path<? extends InterviewEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterviewEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterviewEntity(PathMetadata metadata, PathInits inits) {
        this(InterviewEntity.class, metadata, inits);
    }

    public QInterviewEntity(Class<? extends InterviewEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("member")) : null;
    }

}

