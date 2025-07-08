package com.chatroom.Repository;
import com.chatroom.Entity.userOneId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOneIdRepository extends JpaRepository<userOneId, Long> {
    userOneId findByUserIdOne(Long userIdOne);
}
