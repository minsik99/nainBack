package io.paioneer.nain.blockMember.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlockMemberEntity is a Querydsl query type for BlockMemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockMemberEntity extends EntityPathBase<BlockMemberEntity> {

    private static final long serialVersionUID = -1669340399L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlockMemberEntity blockMemberEntity = new QBlockMemberEntity("blockMemberEntity");

    public final StringPath blockComment = createString("blockComment");

    public final DateTimePath<java.util.Date> blockDate = createDateTime("blockDate", java.util.Date.class);

    public final NumberPath<Integer> blockNo = createNumber("blockNo", Integer.class);

    public final StringPath blockYN = createString("blockYN");

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity memberNo2;

    public QBlockMemberEntity(String variable) {
        this(BlockMemberEntity.class, forVariable(variable), INITS);
    }

    public QBlockMemberEntity(Path<? extends BlockMemberEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlockMemberEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlockMemberEntity(PathMetadata metadata, PathInits inits) {
        this(BlockMemberEntity.class, metadata, inits);
    }

    public QBlockMemberEntity(Class<? extends BlockMemberEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberNo2 = inits.isInitialized("memberNo2") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("memberNo2")) : null;
    }

}

