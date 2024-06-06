package io.paioneer.nain.companylist.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCompanylistEntity is a Querydsl query type for CompanylistEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompanylistEntity extends EntityPathBase<CompanylistEntity> {

    private static final long serialVersionUID = -1972299196L;

    public static final QCompanylistEntity companylistEntity = new QCompanylistEntity("companylistEntity");

    public final DateTimePath<java.time.LocalDateTime> comDate = createDateTime("comDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> comLink = createNumber("comLink", Long.class);

    public final StringPath comName = createString("comName");

    public final NumberPath<Long> comNo = createNumber("comNo", Long.class);

    public final StringPath comTitle = createString("comTitle");

    public QCompanylistEntity(String variable) {
        super(CompanylistEntity.class, forVariable(variable));
    }

    public QCompanylistEntity(Path<? extends CompanylistEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompanylistEntity(PathMetadata metadata) {
        super(CompanylistEntity.class, metadata);
    }

}

