package io.paioneer.nain.resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSkillEntity is a Querydsl query type for SkillEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSkillEntity extends EntityPathBase<SkillEntity> {

    private static final long serialVersionUID = -409969550L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSkillEntity skillEntity = new QSkillEntity("skillEntity");

    public final QResumeEntity resumeEntity;

    public final StringPath skillName = createString("skillName");

    public QSkillEntity(String variable) {
        this(SkillEntity.class, forVariable(variable), INITS);
    }

    public QSkillEntity(Path<? extends SkillEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSkillEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSkillEntity(PathMetadata metadata, PathInits inits) {
        this(SkillEntity.class, metadata, inits);
    }

    public QSkillEntity(Class<? extends SkillEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resumeEntity = inits.isInitialized("resumeEntity") ? new QResumeEntity(forProperty("resumeEntity")) : null;
    }

}

