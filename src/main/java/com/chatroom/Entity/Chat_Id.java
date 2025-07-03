package com.chatroom.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "chat_id")
public class Chat_Id {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

 
    public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	
}
