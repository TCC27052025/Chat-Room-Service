package com.chatroom.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_id_two")
public class userTwoId {

    @Id
    @Column(name = "user_id")  // Primary key column
    private Long userIdTwo;

    @OneToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chatId")  // Foreign key to Chat_Id table
    private Chat_Id chatid;

    // Getters and Setters
    public Long getUserIdTwo() {
        return userIdTwo;
    }

    public void setUserIdTwo(Long userIdTwo) {
        this.userIdTwo = userIdTwo;
    }

    public Chat_Id getChatid() {
        return chatid;
    }

    public void setChatid(Chat_Id chatid) {
        this.chatid = chatid;
    }
}
