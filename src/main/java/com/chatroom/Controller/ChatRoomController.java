package com.chatroom.Controller;

import com.chatroom.DTO.ChatRoomRequestDTO;
import com.chatroom.DTO.ChatRoomResponseDTO;
import com.chatroom.Service.ChatRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat-room")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoomResponseDTO> createChatRoom(@RequestBody ChatRoomRequestDTO dto) {
        ChatRoomResponseDTO response = chatRoomService.createOrGetChatRoom(dto);
        return ResponseEntity.ok(response);
    }

}
