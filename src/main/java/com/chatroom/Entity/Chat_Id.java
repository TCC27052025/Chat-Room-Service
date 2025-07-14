//package com.chatroom.Entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "chat_rooms") // It's good practice to explicitly name the table
//public class Chat_Id {
//
//	@Id
//	@Column(name = "chat_id")
//	private String chatId;
//
//	// This is the field corresponding to the missing method
//	@Column(name = "user_one_id", nullable = false)
//	private Long userOneId;
//
//	@Column(name = "user_two_id", nullable = false)
//	private Long userTwoId;
//
//	// --- Getters and Setters ---
//
//	public String getChatId() {
//		return chatId;
//	}
//
//	public void setChatId(String chatId) {
//		this.chatId = chatId;
//	}
//
//	public Long getUserOneId() {
//		return userOneId;
//	}
//
//	// This is the method the compiler could not find
//	public void setUserOneId(Long userOneId) {
//		this.userOneId = userOneId;
//	}
//
//	public Long getUserTwoId() {
//		return userTwoId;
//	}
//
//	public void setUserTwoId(Long userTwoId) {
//		this.userTwoId = userTwoId;
//	}
//}

package com.chatroom.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_rooms")
public class Chat_Id {

	@Id
	@Column(name = "chat_id")
	private String chatId;

	// ✅ CORRECTED: This now maps to the userOneId entity.
	// The 'mappedBy' attribute indicates that the 'userOneId' entity
	// is the owner of this relationship (it holds the foreign key).
	// CascadeType.ALL ensures that when we save a Chat_Id, the associated
	// userOneId and userTwoId entities are also saved.
	@OneToOne(mappedBy = "chatid", cascade = CascadeType.ALL)
	private userOneId userOne;

	// ✅ CORRECTED: This now maps to the userTwoId entity.
	@OneToOne(mappedBy = "chatid", cascade = CascadeType.ALL)
	private userTwoId userTwo;

	// --- Getters and Setters ---

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public userOneId getUserOne() {
		return userOne;
	}

	public void setUserOne(userOneId userOne) {
		this.userOne = userOne;
	}

	public userTwoId getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(userTwoId userTwo) {
		this.userTwo = userTwo;
	}
}