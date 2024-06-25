package io.paioneer.nain.chat.jpa.repository.chatroom;

import io.paioneer.nain.chat.jpa.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {

    @Query("SELECT COALESCE(MAX(c.chatRoomNo), 0) FROM ChatRoomEntity c")
    Optional<Long> findLastNo();
}
