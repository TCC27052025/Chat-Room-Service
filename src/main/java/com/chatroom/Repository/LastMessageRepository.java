package com.chatroom.Repository;
import com.chatroom.Entity.lastMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastMessageRepository extends JpaRepository<lastMessage, Long> {
}
