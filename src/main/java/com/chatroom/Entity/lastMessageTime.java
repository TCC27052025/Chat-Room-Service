package com.chatroom.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "last_message_time")
public class lastMessageTime {

    @Id
    @Column(name = "chat_id", length = 255)
    private String msgId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id")
    private Chat_Id chatid;

    @Column(name = "msg_time")
    private String msgTime;

    // Getters and Setters
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Chat_Id getChatid() {
        return chatid;
    }

    public void setChatid(Chat_Id chatid) {
        this.chatid = chatid;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }
}
