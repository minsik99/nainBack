package io.paioneer.nain.interview.video.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmotionEntity is a Querydsl query type for EmotionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmotionEntity extends EntityPathBase<EmotionEntity> {

    private static final long serialVersionUID = -983924303L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmotionEntity emotionEntity = new QEmotionEntity("emotionEntity");

    public final NumberPath<Integer> answerEno = createNumber("answerEno", Integer.class);

    public final StringPath emotionName = createString("emotionName");

    public final NumberPath<Long> emotionNo = createNumber("emotionNo", Long.class);

    public final NumberPath<Double> eresultData = createNumber("eresultData", Double.class);

    public final io.paioneer.nain.interview.jpa.entity.QInterviewEntity interviewEntity;

    public QEmotionEntity(String variable) {
        this(EmotionEntity.class, forVariable(variable), INITS);
    }

    public QEmotionEntity(Path<? extends EmotionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmotionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmotionEntity(PathMetadata metadata, PathInits inits) {
        this(EmotionEntity.class, metadata, inits);
    }

    public QEmotionEntity(Class<? extends EmotionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interviewEntity = inits.isInitialized("interviewEntity") ? new io.paioneer.nain.interview.jpa.entity.QInterviewEntity(forProperty("interviewEntity"), inits.get("interviewEntity")) : null;
    }

}

