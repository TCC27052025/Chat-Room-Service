package com.chatroom.DTO;

public class ChatRoomResponse {

    private String chatId;
    private boolean isNew;

    // --- Getters and Setters ---

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}