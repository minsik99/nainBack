package io.paioneer.nain.interview.voice.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoiceEntity is a Querydsl query type for VoiceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoiceEntity extends EntityPathBase<VoiceEntity> {

    private static final long serialVersionUID = 523182961L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoiceEntity voiceEntity = new QVoiceEntity("voiceEntity");

    public final io.paioneer.nain.interview.jpa.entity.QInterviewEntity interviewEntity;

    public final io.paioneer.nain.interview.jpa.entity.QQuestionEntity questionEntity;

    public final StringPath voiceContent = createString("voiceContent");

    public final NumberPath<Long> voiceNo = createNumber("voiceNo", Long.class);

    public QVoiceEntity(String variable) {
        this(VoiceEntity.class, forVariable(variable), INITS);
    }

    public QVoiceEntity(Path<? extends VoiceEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoiceEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoiceEntity(PathMetadata metadata, PathInits inits) {
        this(VoiceEntity.class, metadata, inits);
    }

    public QVoiceEntity(Class<? extends VoiceEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interviewEntity = inits.isInitialized("interviewEntity") ? new io.paioneer.nain.interview.jpa.entity.QInterviewEntity(forProperty("interviewEntity"), inits.get("interviewEntity")) : null;
        this.questionEntity = inits.isInitialized("questionEntity") ? new io.paioneer.nain.interview.jpa.entity.QQuestionEntity(forProperty("questionEntity")) : null;
    }

}

