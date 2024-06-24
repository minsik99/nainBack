package io.paioneer.nain.chat.model.dto;

import io.paioneer.nain.chat.jpa.entity.ChatRoomEntity;
import io.paioneer.nain.chat.jpa.entity.MessageEntity;
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
public class MessageDto {
    private Long messageNo;
    private Long chatRoomNo;
    private Long memberNo;
    private String messageText;
    private Date messageDate;

    public MessageEntity toEntity(ChatRoomEntity chatRoom, MemberEntity member) {
        return MessageEntity.builder()
                .messageText(this.messageText)
                .messageDate(this.messageDate != null ? this.messageDate : new Date())
                .chatRoom(chatRoom)
                .member(member)
                .build();
    }

    public static MessageDto fromEntity(MessageEntity messageEntity) {
        return MessageDto.builder()
                .messageNo(messageEntity.getMessageNo())
                .chatRoomNo(messageEntity.getChatRoom().getChatRoomNo())
                .memberNo(messageEntity.getMember().getMemberNo())
                .messageText(messageEntity.getMessageText())
                .messageDate(messageEntity.getMessageDate())
                .build();
    }
}
