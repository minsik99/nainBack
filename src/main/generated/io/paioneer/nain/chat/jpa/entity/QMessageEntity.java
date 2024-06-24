package io.paioneer.nain.chat.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessageEntity is a Querydsl query type for MessageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessageEntity extends EntityPathBase<MessageEntity> {

    private static final long serialVersionUID = 1890752157L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessageEntity messageEntity = new QMessageEntity("messageEntity");

    public final QChatRoomEntity chatRoom;

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity member;

    public final DateTimePath<java.util.Date> messageDate = createDateTime("messageDate", java.util.Date.class);

    public final NumberPath<Long> messageNo = createNumber("messageNo", Long.class);

    public final StringPath messageText = createString("messageText");

    public QMessageEntity(String variable) {
        this(MessageEntity.class, forVariable(variable), INITS);
    }

    public QMessageEntity(Path<? extends MessageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMessageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessageEntity(PathMetadata metadata, PathInits inits) {
        this(MessageEntity.class, metadata, inits);
    }

    public QMessageEntity(Class<? extends MessageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QChatRoomEntity(forProperty("chatRoom"), inits.get("chatRoom")) : null;
        this.member = inits.isInitialized("member") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("member")) : null;
    }

}

