package io.paioneer.nain.trend.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrendEntity is a Querydsl query type for TrendEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrendEntity extends EntityPathBase<TrendEntity> {

    private static final long serialVersionUID = 1128193860L;

    public static final QTrendEntity trendEntity = new QTrendEntity("trendEntity");

    public final StringPath title = createString("title");

    public final StringPath trendContent = createString("trendContent");

    public final DateTimePath<java.util.Date> trendDate = createDateTime("trendDate", java.util.Date.class);

    public final StringPath trendLink = createString("trendLink");

    public final NumberPath<Long> trendNo = createNumber("trendNo", Long.class);

    public QTrendEntity(String variable) {
        super(TrendEntity.class, forVariable(variable));
    }

    public QTrendEntity(Path<? extends TrendEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrendEntity(PathMetadata metadata) {
        super(TrendEntity.class, metadata);
    }

}

