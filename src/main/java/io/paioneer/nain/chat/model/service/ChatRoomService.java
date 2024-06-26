package io.paioneer.nain.chat.model.service;

import io.paioneer.nain.chat.jpa.repository.chatroom.ChatRoomRepository;
import io.paioneer.nain.chat.model.dto.ChatRoomDto;
import io.paioneer.nain.chat.jpa.entity.ChatRoomEntity;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Transactional
@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomDto createChatRoom(ChatRoomDto chatRoomDto) {
        ChatRoomEntity newChatRoom = toEntity(chatRoomDto);
        newChatRoom.setChatRoomDate(new Date());
        newChatRoom = chatRoomRepository.save(newChatRoom);
        return toDto(newChatRoom);
    }


    private ChatRoomDto toDto(ChatRoomEntity chatRoom) {
        return ChatRoomDto.builder()
                .chatRoomNo(chatRoom.getChatRoomNo())
                .memberNo(chatRoom.getMemberEntity().getMemberNo())
                .name(chatRoom.getName())
                .chatRoomDate(chatRoom.getChatRoomDate())
                .build();
    }

    private ChatRoomEntity toEntity(ChatRoomDto dto) {
        MemberEntity member = new MemberEntity();
        member.setMemberNo(dto.getMemberNo());
        return ChatRoomEntity.builder()
                .name(dto.getName())
                .memberEntity(member)
                .build();
    }
}
