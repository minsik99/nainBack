package io.paioneer.nain.chat.jpa.entity;

import io.paioneer.nain.member.jpa.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_CHATROOM")
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHATROOM_NO")
    private Long chatRoomNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_NO", nullable = false)
    private MemberEntity memberEntity;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "CHATROOM_DATE")
    private Date chatRoomDate;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "DELETED_DATE")
    private Date deletedDate;
}
