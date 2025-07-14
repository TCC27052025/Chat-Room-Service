package com.chatroom.DTO;

public class CreateChatRoomRequest {

    private Long userOneId;
    private Long userTwoId;

    // --- Getters and Setters ---

    public Long getUserOneId() {
        return userOneId;
    }

    public void setUserOneId(Long userOneId) {
        this.userOneId = userOneId;
    }

    public Long getUserTwoId() {
        return userTwoId;
    }

    public void setUserTwoId(Long userTwoId) {
        this.userTwoId = userTwoId;
    }
}