package io.paioneer.nain.interview.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnalysisEntity is a Querydsl query type for AnalysisEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnalysisEntity extends EntityPathBase<AnalysisEntity> {

    private static final long serialVersionUID = 656819295L;

    public static final QAnalysisEntity analysisEntity = new QAnalysisEntity("analysisEntity");

    public final StringPath analysisCategory = createString("analysisCategory");

    public final StringPath analysisContent = createString("analysisContent");

    public final NumberPath<Long> analysisNo = createNumber("analysisNo", Long.class);

    public final StringPath analysisType = createString("analysisType");

    public QAnalysisEntity(String variable) {
        super(AnalysisEntity.class, forVariable(variable));
    }

    public QAnalysisEntity(Path<? extends AnalysisEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnalysisEntity(PathMetadata metadata) {
        super(AnalysisEntity.class, metadata);
    }

}

