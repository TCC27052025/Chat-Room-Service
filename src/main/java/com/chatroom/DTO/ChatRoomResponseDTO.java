package com.chatroom.DTO;

public class ChatRoomResponseDTO {

    private String chatId;
    private boolean isNew;

    public ChatRoomResponseDTO() {}

    public ChatRoomResponseDTO(String chatId, boolean isNew) {
        this.chatId = chatId;
        this.isNew = isNew;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }
}
