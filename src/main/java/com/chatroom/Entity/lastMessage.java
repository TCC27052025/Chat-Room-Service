package com.chatroom.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "last_message")
public class lastMessage {

    @Id
    private Long msgId;
    public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	@OneToOne
    @MapsId
    @JoinColumn(name = "chat_id", referencedColumnName = "chatId")
    private Chat_Id chatid;

    @Column(name = "msg")
    private String msg;

    // Getters and Setters
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
