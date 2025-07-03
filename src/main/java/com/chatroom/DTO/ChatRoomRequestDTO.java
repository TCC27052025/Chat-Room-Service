package com.chatroom.DTO;

public class ChatRoomRequestDTO {
    private Long userOneId;
    private Long userTwoId;

    // Constructors
    public ChatRoomRequestDTO() {}

    public ChatRoomRequestDTO(Long userOneId, Long userTwoId) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
    }

    // Getters and Setters
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
