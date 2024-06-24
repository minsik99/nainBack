package io.paioneer.nain.chat.jpa.repository.chatroom;

import io.paioneer.nain.chat.jpa.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    List<ChatRoomEntity> findAllByOrderByChatRoomDateDesc();
}
