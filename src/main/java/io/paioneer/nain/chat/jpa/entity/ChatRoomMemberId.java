package io.paioneer.nain.chat.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomMemberId implements Serializable {

    @Column(name = "CHATROOM_NO")
    private Long chatRoomNo;

    @Column(name = "MEMBER_NO")
    private Long memberNo;
}
