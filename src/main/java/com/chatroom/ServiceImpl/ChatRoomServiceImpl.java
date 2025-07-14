//package com.chatroom.ServiceImpl;
//
//import com.chatroom.DTO.ChatRoomRequestDTO;
//import com.chatroom.DTO.ChatRoomResponseDTO;
//import com.chatroom.Entity.*;
//import com.chatroom.Repository.*;
//import com.chatroom.Service.ChatRoomService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.Optional;
//
//@Service
//public class ChatRoomServiceImpl implements ChatRoomService {
//
//    @Autowired
//    private ChatIdRepository chatIdRepo;
//
//    @Autowired
//    private UserOneIdRepository userOneRepo;
//
//    @Autowired
//    private UserTwoIdRepository userTwoRepo;
//
//    @Autowired
//    private LastMessageRepository messageRepo;
//
//    @Autowired
//    private LastMessageTimeRepository messageTimeRepo;
//
//    @Override
//    public ChatRoomResponseDTO createOrGetChatRoom(ChatRoomRequestDTO requestDTO) {
//        Long userOneId = requestDTO.getUserOneId();
//        Long userTwoId = requestDTO.getUserTwoId();
//
//        // ✅ Check if chat already exists
//        Optional<Chat_Id> existingChat = chatIdRepo.findChatByUserIds(userOneId, userTwoId);
//
//        if (existingChat.isPresent()) {
//            return new ChatRoomResponseDTO("C" + existingChat.get().getChatId(), false);
//        }
//
//        // ❌ No existing chat – create new chat
//        Chat_Id chat = chatIdRepo.save(new Chat_Id());
//
//        // Save userOne
//        userOneId userOne = new userOneId();
//        userOne.setUserIdOne(userOneId);
//        userOne.setChatid(chat);
//        userOneRepo.save(userOne);
//
//        // Save userTwo
//        userTwoId userTwo = new userTwoId();
//        userTwo.setUserIdTwo(userTwoId);
//        userTwo.setChatid(chat);
//        userTwoRepo.save(userTwo);
//
//        // Save lastMessage as null
//        lastMessage msg = new lastMessage();
//        msg.setChatid(chat);
//        msg.setMsg(null);
//        messageRepo.save(msg);
//
//        // Save lastMessageTime as null
//        lastMessageTime msgTime = new lastMessageTime();
//        msgTime.setChatid(chat);
//        msgTime.setMsgTime(null);
//        messageTimeRepo.save(msgTime);
//
//        return new ChatRoomResponseDTO("C" + chat.getChatId(), true);
//    }
//}

//package com.chatroom.ServiceImpl;
//
//import com.chatroom.DTO.ChatRoomResponseDTO;
//import com.chatroom.Entity.Chat_Id; // Assuming this is your main chat room entity
//import com.chatroom.Repository.ChatIdRepository; // You need to create this repository
//import com.chatroom.Service.ChatRoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class ChatRoomServiceImpl implements ChatRoomService {
//
//    private final ChatIdRepository chatIdRepository;
//
//    @Autowired
//    public ChatRoomServiceImpl(ChatIdRepository chatIdRepository) {
//        this.chatIdRepository = chatIdRepository;
//    }
//
//    @Override
//    @Transactional
//    public ChatRoomResponseDTO findOrCreateChatRoom(Long userOneId, Long userTwoId) {
//        // This query is crucial. It checks for a room between user1 and user2, regardless of order.
//        Optional<Chat_Id> existingRoom = chatIdRepository.findByUserOneIdAndUserTwoId(userOneId, userTwoId);
//
//        if (existingRoom.isPresent()) {
//            // Room already exists, return its ID
//            System.out.println("Found existing chat room: " + existingRoom.get().getChatId());
//            return new ChatRoomResponseDTO(existingRoom.get().getChatId(), false);
//        } else {
//            // Room does not exist, create a new one
//            System.out.println("No existing room found. Creating a new one.");
//            Chat_Id newChatRoom = new Chat_Id();
//            newChatRoom.setChatId(UUID.randomUUID().toString());
//            newChatRoom.setUserOneId(userOneId);
//            newChatRoom.setUserTwoId(userTwoId);
//            // Set any other required fields
//
//            Chat_Id savedRoom = chatIdRepository.save(newChatRoom);
//
//            return new ChatRoomResponseDTO(savedRoom.getChatId(), true);
//        }
//    }
//}
//package com.chatroom.ServiceImpl;
//
//import com.chatroom.DTO.ChatRoomResponse;
//import com.chatroom.Entity.Chat_Id;
//import com.chatroom.Repository.ChatIdRepository;
//import com.chatroom.Service.ChatRoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class ChatRoomServiceImpl implements ChatRoomService {
//
//    private final ChatIdRepository chatIdRepository;
//
//    @Autowired
//    public ChatRoomServiceImpl(ChatIdRepository chatIdRepository) {
//        this.chatIdRepository = chatIdRepository;
//    }
//
//    @Override
//    public ChatRoomResponse findOrCreateChatRoom(Long userOneId, Long userTwoId) {
//        // Use the bidirectional query to find a room regardless of user order.
//        Optional<Chat_Id> existingRoom = chatIdRepository.findByUserOneIdAndUserTwoId(userOneId, userTwoId);
//
//        if (existingRoom.isPresent()) {
//            // Room already exists, build a response with isNew = false.
//            ChatRoomResponse response = new ChatRoomResponse();
//            response.setChatId(existingRoom.get().getChatId());
//            response.setNew(false);
//            return response;
//        } else {
//            // Room does not exist, create a new one.
//            Chat_Id newChatRoom = new Chat_Id();
//            newChatRoom.setChatId(UUID.randomUUID().toString());
//            newChatRoom.setUserOneId(userOneId);
//            newChatRoom.setUserTwoId(userTwoId);
//            Chat_Id savedRoom = chatIdRepository.save(newChatRoom);
//
//            // Build a response with isNew = true.
//            ChatRoomResponse response = new ChatRoomResponse();
//            response.setChatId(savedRoom.getChatId());
//            response.setNew(true);
//            return response;
//        }
//    }
//}

package com.chatroom.ServiceImpl;

import com.chatroom.DTO.ChatRoomResponse;
import com.chatroom.Entity.Chat_Id;
import com.chatroom.Entity.userOneId;
import com.chatroom.Entity.userTwoId;
import com.chatroom.Repository.ChatIdRepository;
import com.chatroom.Service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatIdRepository chatIdRepository;

    @Autowired
    public ChatRoomServiceImpl(ChatIdRepository chatIdRepository) {
        this.chatIdRepository = chatIdRepository;
    }

    @Override
    // ✅ CRITICAL: Ensures all database saves succeed or fail together as one atomic unit.
    @Transactional
    public ChatRoomResponse findOrCreateChatRoom(Long userOneIdValue, Long userTwoIdValue) {
        // Use the corrected repository method to find an existing room.
        Optional<Chat_Id> existingRoom = chatIdRepository.findByUserOneIdAndUserTwoId(userOneIdValue, userTwoIdValue);

        if (existingRoom.isPresent()) {
            // Room already exists, build a response with isNew = false.
            ChatRoomResponse response = new ChatRoomResponse();
            response.setChatId(existingRoom.get().getChatId());
            response.setNew(false);
            return response;
        } else {
            // ✅ CORRECTED LOGIC: Create and link all related entities.
            // 1. Create the parent Chat_Id object.
            Chat_Id newChatRoom = new Chat_Id();
            newChatRoom.setChatId(UUID.randomUUID().toString());

            // 2. Create the first participant entity and link it to the parent.
            userOneId userOne = new userOneId();
            userOne.setUserIdOne(userOneIdValue);
            userOne.setChatid(newChatRoom);

            // 3. Create the second participant entity and link it to the parent.
            userTwoId userTwo = new userTwoId();
            userTwo.setUserIdTwo(userTwoIdValue);
            userTwo.setChatid(newChatRoom);

            // 4. Link the parent back to its children. This is what the compiler was asking for.
            newChatRoom.setUserOne(userOne);
            newChatRoom.setUserTwo(userTwo);

            // 5. Save the parent. Because of CascadeType.ALL, JPA will also save the children.
            Chat_Id savedRoom = chatIdRepository.save(newChatRoom);

            // Build a response with isNew = true.
            ChatRoomResponse response = new ChatRoomResponse();
            response.setChatId(savedRoom.getChatId());
            response.setNew(true);
            return response;
        }
    }
}