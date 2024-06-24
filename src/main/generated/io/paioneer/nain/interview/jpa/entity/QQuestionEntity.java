package io.paioneer.nain.interview.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQuestionEntity is a Querydsl query type for QuestionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionEntity extends EntityPathBase<QuestionEntity> {

    private static final long serialVersionUID = -766779927L;

    public static final QQuestionEntity questionEntity = new QQuestionEntity("questionEntity");

    public final StringPath qContent = createString("qContent");

    public final NumberPath<Long> qNo = createNumber("qNo", Long.class);

    public final StringPath qType = createString("qType");

    public QQuestionEntity(String variable) {
        super(QuestionEntity.class, forVariable(variable));
    }

    public QQuestionEntity(Path<? extends QuestionEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionEntity(PathMetadata metadata) {
        super(QuestionEntity.class, metadata);
    }

}

