package com.chatroom.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "last_message")
public class lastMessage {

    @Id
    @Column(name = "chat_id", nullable = false, length = 255)
    private String chatId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    private Chat_Id chatid;

    @Column(name = "msg")
    private String msg;

    public lastMessage() {}

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Chat_Id getChatid() {
        return chatid;
    }

    public void setChatid(Chat_Id chatid) {
        this.chatid = chatid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

