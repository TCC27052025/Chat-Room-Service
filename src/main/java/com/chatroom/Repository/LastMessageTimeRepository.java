package com.chatroom.Repository;
import com.chatroom.Entity.lastMessageTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastMessageTimeRepository extends JpaRepository<lastMessageTime, Long> {
}
