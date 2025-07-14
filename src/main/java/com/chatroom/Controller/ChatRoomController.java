//package com.chatroom.Controller;
//
//import com.chatroom.DTO.CreateChatRoomRequest; // You will need to create these DTOs
//import com.chatroom.DTO.ChatRoomResponse;
//// import com.chatroom.Service.ChatRoomPersistenceService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/v1/chat-room")
//public class ChatRoomController {
//
//    private static final Logger log = LoggerFactory.getLogger(ChatRoomController.class);
//
//    // @Autowired
//    // private ChatRoomPersistenceService chatRoomPersistenceService;
//
//    @PostMapping
//    public ResponseEntity<ChatRoomResponse> createChatRoom(@RequestBody CreateChatRoomRequest request) {
//        // Business Logic:
//        // 1. Validate participant IDs.
//        if (request.getParticipantIds() == null || request.getParticipantIds().size() != 2) {
//            log.error("Invalid participant list for creating a new chat room.");
//            return ResponseEntity.badRequest().build();
//        }
//
//        // 2. Optional: Check if a room for these exact participants already exists to avoid duplicates.
//        // 3. Generate a unique ID for the new room.
//        String newChatId = UUID.randomUUID().toString();
//
//        // 4. Persist the new chat room and its participants to your database.
//        // chatRoomPersistenceService.save(newChatId, request.getParticipantIds());
//        log.info("API: Creating chat room {} for participants {}", newChatId, request.getParticipantIds());
//
//        // 5. Build and return the response.
//        ChatRoomResponse response = new ChatRoomResponse();
//        response.setChatId(newChatId);
//        response.setParticipantIds(request.getParticipantIds());
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//}

//package com.chatroom.Controller;
//
//import com.chatroom.DTO.CreateChatRoomRequest;
//import com.chatroom.DTO.ChatRoomResponse;
//import com.chatroom.Entity.Chat_Id; // Make sure you have this entity
//import com.chatroom.Service.ChatRoomService; // You will create this service
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/chat-room")
//public class ChatRoomController {
//
//    private static final Logger log = LoggerFactory.getLogger(ChatRoomController.class);
//
//    private final ChatRoomService chatRoomService;
//
//    // ✅ Use constructor injection for required dependencies
//    @Autowired
//    public ChatRoomController(ChatRoomService chatRoomService) {
//        this.chatRoomService = chatRoomService;
//    }
//
//    @PostMapping
//    public ResponseEntity<ChatRoomResponse> createChatRoom(@RequestBody CreateChatRoomRequest request) {
//        // 1. Validate participant IDs.
//        if (request.getParticipantIds() == null || request.getParticipantIds().size() != 2) {
//            log.error("Invalid participant list for creating a new chat room. Must contain exactly 2 IDs.");
//            return ResponseEntity.badRequest().build();
//        }
//
//        // 2. Delegate creation logic to the service
//        // This service will handle checking for duplicates and saving to the DB.
//        Chat_Id savedRoom = chatRoomService.findOrCreateChatRoom(
//                request.getParticipantIds().get(0),
//                request.getParticipantIds().get(1)
//        );
//
//        log.info("API: Ensured chat room {} exists for participants {}", savedRoom.getChatId(), request.getParticipantIds());
//
//        // 3. Build and return the response.
//        ChatRoomResponse response = new ChatRoomResponse();
//        response.setChatId(savedRoom.getChatId());
//        response.setParticipantIds(request.getParticipantIds());
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//}
package com.chatroom.Controller;

import com.chatroom.DTO.CreateChatRoomRequest;
import com.chatroom.DTO.ChatRoomResponse;
import com.chatroom.Service.ChatRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat-room")
public class ChatRoomController {

    private static final Logger log = LoggerFactory.getLogger(ChatRoomController.class);

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    /**
     * Finds an existing chat room or creates a new one for the two specified users.
     * This endpoint adheres to the LLD requiring specific request and response formats.
     *
     * @param request The request body containing the IDs of the two participants.
     * @return A ResponseEntity containing the chat room details and an `isNew` flag.
     */
    @PostMapping
    public ResponseEntity<ChatRoomResponse> createChatRoom(@RequestBody CreateChatRoomRequest request) {
        // 1. Validate the incoming request.
        if (request.getUserOneId() == null || request.getUserTwoId() == null) {
            log.error("Invalid request: Both userOneId and userTwoId must be provided.");
            return ResponseEntity.badRequest().build();
        }

        // 2. Delegate logic to the service, which now returns the response DTO directly.
        ChatRoomResponse response = chatRoomService.findOrCreateChatRoom(
                request.getUserOneId(),
                request.getUserTwoId()
        );

        log.info("API: Ensured chat room {} exists for participants {} and {}. Was new: {}",
                response.getChatId(), request.getUserOneId(), request.getUserTwoId(), response.isNew());

        // 3. Return the appropriate HTTP status based on whether a resource was created.
        HttpStatus status = response.isNew() ? HttpStatus.CREATED : HttpStatus.OK;

        return new ResponseEntity<>(response, status);
    }
}