package com.chatroom.Service;

import com.chatroom.DTO.ChatRoomResponse;

public interface ChatRoomService {
    ChatRoomResponse findOrCreateChatRoom(Long userOneId, Long userTwoId);
}