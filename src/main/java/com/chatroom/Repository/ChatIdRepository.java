//package com.chatroom.Repository;
//
//import com.chatroom.Entity.Chat_Id;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.Optional;
//
//public interface ChatIdRepository extends JpaRepository<Chat_Id, String> {
//
//    /**
//     * Finds a chat room between two users by their IDs, regardless of which user is stored
//     * as userOneId or userTwoId in the database. This ensures that a unique chat room
//     * is found for any pair of users.
//     *
//     * @param userOneId The ID of the first user.
//     * @param userTwoId The ID of the second user.
//     * @return An Optional containing the Chat_Id if found, otherwise an empty Optional.
//     */
//    @Query("SELECT c FROM Chat_Id c WHERE (c.userOneId = :userOneId AND c.userTwoId = :userTwoId) OR (c.userOneId = :userTwoId AND c.userTwoId = :userOneId)")
//    Optional<Chat_Id> findByUserOneIdAndUserTwoId(@Param("userOneId") Long userOneId, @Param("userTwoId") Long userTwoId);
//}

package com.chatroom.Repository;

import com.chatroom.Entity.Chat_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatIdRepository extends JpaRepository<Chat_Id, String> {

    /**
     * Finds a chat room by traversing the entity relationships to the user tables.
     * This query is now compatible with your relational entity design.
     */
    // ✅ CORRECTED: The query now joins across the related entities.
    // It checks the 'userIdOne' field within the 'userOne' object.
    @Query("SELECT c FROM Chat_Id c WHERE " +
            "(c.userOne.userIdOne = :userOneId AND c.userTwo.userIdTwo = :userTwoId) OR " +
            "(c.userOne.userIdOne = :userTwoId AND c.userTwo.userIdTwo = :userOneId)")
    Optional<Chat_Id> findByUserOneIdAndUserTwoId(@Param("userOneId") Long userOneId, @Param("userTwoId") Long userTwoId);
}