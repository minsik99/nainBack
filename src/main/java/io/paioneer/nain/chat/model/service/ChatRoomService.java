package io.paioneer.nain.chat.model.service;

import io.paioneer.nain.chat.jpa.repository.chatroom.ChatRoomRepository;
import io.paioneer.nain.chat.model.dto.ChatRoomDto;
import io.paioneer.nain.chat.jpa.entity.ChatRoomEntity;
import io.paioneer.nain.member.jpa.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public List<ChatRoomDto> getAllChatRooms() {
        List<ChatRoomEntity> chatRooms = chatRoomRepository.findAllByOrderByChatRoomDateDesc();
        return chatRooms.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ChatRoomDto createChatRoom(ChatRoomDto chatRoomDto) {
        ChatRoomEntity chatRoom = toEntity(chatRoomDto);
        chatRoom.setChatRoomDate(new Date());
        chatRoom = chatRoomRepository.save(chatRoom);
        return toDto(chatRoom);
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
        ChatRoomEntity chatRoom = ChatRoomEntity.builder()
                .chatRoomNo(dto.getChatRoomNo())
                .name(dto.getName())
                .chatRoomDate(dto.getChatRoomDate())
                .build();

        if (dto.getMemberNo() != null) {
            MemberEntity member = new MemberEntity();
            member.setMemberNo(dto.getMemberNo());
            chatRoom.setMemberEntity(member);
        }

        return chatRoom;
    }
}
