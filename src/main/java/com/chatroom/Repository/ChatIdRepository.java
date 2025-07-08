package com.chatroom.Repository;

import com.chatroom.Entity.Chat_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatIdRepository extends JpaRepository<Chat_Id, String> {

    @Query("SELECT c FROM Chat_Id c " +
           "JOIN com.chatroom.Entity.userOneId u1 ON u1.chatid = c " +
           "JOIN com.chatroom.Entity.userTwoId u2 ON u2.chatid = c " +
           "WHERE ((u1.userIdOne = :userOne AND u2.userIdTwo = :userTwo) " +
           "OR (u1.userIdOne = :userTwo AND u2.userIdTwo = :userOne))")
    Optional<Chat_Id> findChatByUserIds(@Param("userOne") Long userOneId,
                                        @Param("userTwo") Long userTwoId);
}
