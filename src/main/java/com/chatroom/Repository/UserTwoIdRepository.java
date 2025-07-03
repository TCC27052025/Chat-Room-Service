package com.chatroom.Repository;
import com.chatroom.Entity.userTwoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTwoIdRepository extends JpaRepository<userTwoId, Long> {
    userTwoId findByUserIdTwo(Long userIdTwo);
}
