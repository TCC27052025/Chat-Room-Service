package com.chatroom.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_id")
public class Chat_Id {

	@Id
	@Column(name = "chat_id", nullable = false, length = 255)
	private String chatId;

	public Chat_Id() {}

	public Chat_Id(String chatId) {
		this.chatId = chatId;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
}


