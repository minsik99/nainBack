package io.paioneer.nain.resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAcceptedKeywordEntity is a Querydsl query type for AcceptedKeywordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAcceptedKeywordEntity extends EntityPathBase<AcceptedKeywordEntity> {

    private static final long serialVersionUID = 519673891L;

    public static final QAcceptedKeywordEntity acceptedKeywordEntity = new QAcceptedKeywordEntity("acceptedKeywordEntity");

    public final StringPath jobTitle = createString("jobTitle");

    public final StringPath keyword = createString("keyword");

    public QAcceptedKeywordEntity(String variable) {
        super(AcceptedKeywordEntity.class, forVariable(variable));
    }

    public QAcceptedKeywordEntity(Path<? extends AcceptedKeywordEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAcceptedKeywordEntity(PathMetadata metadata) {
        super(AcceptedKeywordEntity.class, metadata);
    }

}

