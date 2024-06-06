package io.paioneer.nain.trend.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSearchWordEntity is a Querydsl query type for SearchWordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSearchWordEntity extends EntityPathBase<SearchWordEntity> {

    private static final long serialVersionUID = 1389330033L;

    public static final QSearchWordEntity searchWordEntity = new QSearchWordEntity("searchWordEntity");

    public final StringPath keyword = createString("keyword");

    public final NumberPath<Integer> searchwordCount = createNumber("searchwordCount", Integer.class);

    public final NumberPath<Long> searchwordNo = createNumber("searchwordNo", Long.class);

    public QSearchWordEntity(String variable) {
        super(SearchWordEntity.class, forVariable(variable));
    }

    public QSearchWordEntity(Path<? extends SearchWordEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSearchWordEntity(PathMetadata metadata) {
        super(SearchWordEntity.class, metadata);
    }

}

