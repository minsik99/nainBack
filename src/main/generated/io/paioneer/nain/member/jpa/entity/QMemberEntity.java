package io.paioneer.nain.member.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = 1389880172L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final BooleanPath admin = createBoolean("admin");

    public final DateTimePath<java.time.LocalDateTime> expireDate = createDateTime("expireDate", java.time.LocalDateTime.class);

    public final StringPath loginType = createString("loginType");

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberName = createString("memberName");

    public final StringPath memberNickName = createString("memberNickName");

    public final NumberPath<Long> memberNo = createNumber("memberNo", Long.class);

    public final StringPath memberPwd = createString("memberPwd");

    public final DateTimePath<java.time.LocalDateTime> memberUpdate = createDateTime("memberUpdate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> paymentDate = createDateTime("paymentDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> signUpDate = createDateTime("signUpDate", java.time.LocalDateTime.class);

    public final StringPath snsAccessToken = createString("snsAccessToken");

    public final StringPath subscribeYN = createString("subscribeYN");

    public final DateTimePath<java.time.LocalDateTime> withDrawalDate = createDateTime("withDrawalDate", java.time.LocalDateTime.class);

    public QMemberEntity(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}

