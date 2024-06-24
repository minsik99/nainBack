package io.paioneer.nain.chat.model.dto;

import io.paioneer.nain.chat.jpa.entity.ChatRoomEntity;
import io.paioneer.nain.chat.jpa.entity.ChatRoomMemberEntity;
import io.paioneer.nain.chat.jpa.entity.ChatRoomMemberId;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ChatRoomMemberDto {
    private Long chatRoomNo;
    private Long memberNo;
    private Date joinDate;

    public ChatRoomMemberEntity toEntity() {
        ChatRoomMemberId id = new ChatRoomMemberId(chatRoomNo, memberNo);
        ChatRoomMemberEntity chatRoomMemberEntity = ChatRoomMemberEntity.builder()
                .id(id)
                .joinDate(this.joinDate)
                .build();

        if (this.chatRoomNo != null) {
            ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
            chatRoomEntity.setChatRoomNo(this.chatRoomNo);
            chatRoomMemberEntity.setChatRoom(chatRoomEntity);
        }

        if (this.memberNo != null) {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setMemberNo(this.memberNo);
            chatRoomMemberEntity.setMember(memberEntity);
        }

        return chatRoomMemberEntity;
    }
}
