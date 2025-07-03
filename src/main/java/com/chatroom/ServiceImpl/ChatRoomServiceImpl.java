package com.chatroom.ServiceImpl;

import com.chatroom.DTO.ChatRoomRequestDTO;
import com.chatroom.DTO.ChatRoomResponseDTO;
import com.chatroom.Entity.*;
import com.chatroom.Repository.*;
import com.chatroom.Service.ChatRoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatIdRepository chatIdRepo;

    @Autowired
    private UserOneIdRepository userOneRepo;

    @Autowired
    private UserTwoIdRepository userTwoRepo;

    @Autowired
    private LastMessageRepository messageRepo;

    @Autowired
    private LastMessageTimeRepository messageTimeRepo;

    @Override
    public ChatRoomResponseDTO createOrGetChatRoom(ChatRoomRequestDTO requestDTO) {
        Long userOneId = requestDTO.getUserOneId();
        Long userTwoId = requestDTO.getUserTwoId();

        // ✅ Check if chat already exists
        Optional<Chat_Id> existingChat = chatIdRepo.findChatByUserIds(userOneId, userTwoId);

        if (existingChat.isPresent()) {
            return new ChatRoomResponseDTO("C" + existingChat.get().getChatId(), false);
        }

        // ❌ No existing chat – create new chat
        Chat_Id chat = chatIdRepo.save(new Chat_Id());

        // Save userOne
        userOneId userOne = new userOneId();
        userOne.setUserIdOne(userOneId);
        userOne.setChatid(chat);
        userOneRepo.save(userOne);

        // Save userTwo
        userTwoId userTwo = new userTwoId();
        userTwo.setUserIdTwo(userTwoId);
        userTwo.setChatid(chat);
        userTwoRepo.save(userTwo);

        // Save lastMessage as null
        lastMessage msg = new lastMessage();
        msg.setChatid(chat);
        msg.setMsg(null);
        messageRepo.save(msg);

        // Save lastMessageTime as null
        lastMessageTime msgTime = new lastMessageTime();
        msgTime.setChatid(chat);
        msgTime.setMsgTime(null);
        messageTimeRepo.save(msgTime);

        return new ChatRoomResponseDTO("C" + chat.getChatId(), true);
    }
}
