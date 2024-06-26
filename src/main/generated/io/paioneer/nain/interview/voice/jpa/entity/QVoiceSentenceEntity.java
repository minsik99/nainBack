package io.paioneer.nain.interview.voice.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoiceSentenceEntity is a Querydsl query type for VoiceSentenceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoiceSentenceEntity extends EntityPathBase<VoiceSentenceEntity> {

    private static final long serialVersionUID = 1416552884L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoiceSentenceEntity voiceSentenceEntity = new QVoiceSentenceEntity("voiceSentenceEntity");

    public final NumberPath<Float> negative = createNumber("negative", Float.class);

    public final NumberPath<Float> positive = createNumber("positive", Float.class);

    public final StringPath sentence = createString("sentence");

    public final QVoiceEntity voiceEntity;

    public final NumberPath<Long> vsNO = createNumber("vsNO", Long.class);

    public QVoiceSentenceEntity(String variable) {
        this(VoiceSentenceEntity.class, forVariable(variable), INITS);
    }

    public QVoiceSentenceEntity(Path<? extends VoiceSentenceEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoiceSentenceEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoiceSentenceEntity(PathMetadata metadata, PathInits inits) {
        this(VoiceSentenceEntity.class, metadata, inits);
    }

    public QVoiceSentenceEntity(Class<? extends VoiceSentenceEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.voiceEntity = inits.isInitialized("voiceEntity") ? new QVoiceEntity(forProperty("voiceEntity"), inits.get("voiceEntity")) : null;
    }

}

