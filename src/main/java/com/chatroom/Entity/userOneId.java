package com.chatroom.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_id_one")
public class userOneId {

    @Id
    @Column(name = "user_id")  // Primary key column
    private Long userIdOne;

    @OneToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chatId")  // Foreign key to chat table
    private Chat_Id chatid;

    // Getters and Setters
    public Long getUserIdOne() {
        return userIdOne;
    }

    public void setUserIdOne(Long userIdOne) {
        this.userIdOne = userIdOne;
    }

    public Chat_Id getChatid() {
        return chatid;
    }

    public void setChatid(Chat_Id chatid) {
        this.chatid = chatid;
    }
}
