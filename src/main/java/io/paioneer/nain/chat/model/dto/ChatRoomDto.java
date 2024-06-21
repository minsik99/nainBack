package io.paioneer.nain.chat.model.dto;

import io.paioneer.nain.chat.jpa.entity.ChatRoomEntity;
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
public class ChatRoomDto {
    private Long chatRoomNo;
    private Long memberNo;
    private String name;
    private Date chatRoomDate;

    public ChatRoomEntity toEntity() {
        ChatRoomEntity chatRoomEntity = ChatRoomEntity.builder()
                .chatRoomNo(this.chatRoomNo)
                .name(this.name)
                .chatRoomDate(this.chatRoomDate)
                .build();

        if (this.memberNo != null) {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setMemberNo(this.memberNo);
            chatRoomEntity.setMemberEntity(memberEntity);
        }
        return chatRoomEntity;
    }
}
