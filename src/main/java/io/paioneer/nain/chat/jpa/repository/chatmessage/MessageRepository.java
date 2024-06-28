package io.paioneer.nain.chat.jpa.repository.chatmessage;

import io.paioneer.nain.chat.jpa.entity.MessageEntity;
import io.paioneer.nain.chat.model.dto.MessageDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByChatRoom_ChatRoomNo(Long chatRoomNo, Sort sort);
}
