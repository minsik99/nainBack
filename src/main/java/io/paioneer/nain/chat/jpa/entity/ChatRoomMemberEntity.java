package io.paioneer.nain.chat.jpa.entity;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_CHATROOM_MEMBERS")
public class ChatRoomMemberEntity {

    @EmbeddedId
    private ChatRoomMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("chatRoomNo")
    @JoinColumn(name = "CHATROOM_NO")
    private ChatRoomEntity chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberNo")
    @JoinColumn(name = "MEMBER_NO")
    private MemberEntity member;

    @Column(name = "JOIN_DATE")
    private Date joinDate;
}

