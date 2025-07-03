package com.chatroom.Service;

import com.chatroom.DTO.ChatRoomRequestDTO;
import com.chatroom.DTO.ChatRoomResponseDTO;

public interface ChatRoomService {
    ChatRoomResponseDTO createOrGetChatRoom(ChatRoomRequestDTO requestDTO);
}
