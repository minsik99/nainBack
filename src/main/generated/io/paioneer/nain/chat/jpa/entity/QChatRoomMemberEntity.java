package io.paioneer.nain.chat.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoomMemberEntity is a Querydsl query type for ChatRoomMemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoomMemberEntity extends EntityPathBase<ChatRoomMemberEntity> {

    private static final long serialVersionUID = -1947009059L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoomMemberEntity chatRoomMemberEntity = new QChatRoomMemberEntity("chatRoomMemberEntity");

    public final QChatRoomEntity chatRoom;

    public final QChatRoomMemberId id;

    public final DateTimePath<java.util.Date> joinDate = createDateTime("joinDate", java.util.Date.class);

    public final io.paioneer.nain.member.jpa.entity.QMemberEntity member;

    public QChatRoomMemberEntity(String variable) {
        this(ChatRoomMemberEntity.class, forVariable(variable), INITS);
    }

    public QChatRoomMemberEntity(Path<? extends ChatRoomMemberEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoomMemberEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoomMemberEntity(PathMetadata metadata, PathInits inits) {
        this(ChatRoomMemberEntity.class, metadata, inits);
    }

    public QChatRoomMemberEntity(Class<? extends ChatRoomMemberEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QChatRoomEntity(forProperty("chatRoom"), inits.get("chatRoom")) : null;
        this.id = inits.isInitialized("id") ? new QChatRoomMemberId(forProperty("id")) : null;
        this.member = inits.isInitialized("member") ? new io.paioneer.nain.member.jpa.entity.QMemberEntity(forProperty("member")) : null;
    }

}

