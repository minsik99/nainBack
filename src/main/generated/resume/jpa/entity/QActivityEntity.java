package resume.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QActivityEntity is a Querydsl query type for ActivityEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActivityEntity extends EntityPathBase<ActivityEntity> {

    private static final long serialVersionUID = -1824063223L;

    public static final QActivityEntity activityEntity = new QActivityEntity("activityEntity");

    public final StringPath activityDescription = createString("activityDescription");

    public final StringPath activityName = createString("activityName");

    public final NumberPath<Long> activityNo = createNumber("activityNo", Long.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final StringPath organizer = createString("organizer");

    public final NumberPath<Long> resumeNo = createNumber("resumeNo", Long.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QActivityEntity(String variable) {
        super(ActivityEntity.class, forVariable(variable));
    }

    public QActivityEntity(Path<? extends ActivityEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActivityEntity(PathMetadata metadata) {
        super(ActivityEntity.class, metadata);
    }

}

