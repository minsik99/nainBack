package io.paioneer.nain.interview.video.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVideoEntity is a Querydsl query type for VideoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideoEntity extends EntityPathBase<VideoEntity> {

    private static final long serialVersionUID = -533977455L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideoEntity videoEntity = new QVideoEntity("videoEntity");

    public final NumberPath<Integer> answerNo = createNumber("answerNo", Integer.class);

    public final io.paioneer.nain.interview.jpa.entity.QInterviewEntity interviewEntity;

    public final StringPath itvType = createString("itvType");

    public final NumberPath<Long> videoNo = createNumber("videoNo", Long.class);

    public final NumberPath<Double> videoScore = createNumber("videoScore", Double.class);

    public QVideoEntity(String variable) {
        this(VideoEntity.class, forVariable(variable), INITS);
    }

    public QVideoEntity(Path<? extends VideoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVideoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVideoEntity(PathMetadata metadata, PathInits inits) {
        this(VideoEntity.class, metadata, inits);
    }

    public QVideoEntity(Class<? extends VideoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interviewEntity = inits.isInitialized("interviewEntity") ? new io.paioneer.nain.interview.jpa.entity.QInterviewEntity(forProperty("interviewEntity"), inits.get("interviewEntity")) : null;
    }

}

