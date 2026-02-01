package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.main.entity.ChatMessage;
import com.exe201.tutorlink.main.repository.IChatMessageRepository;
import com.exe201.tutorlink.main.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired private IChatMessageRepository chatMessageRepository;
    @Autowired private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSender().getId(), chatMessage.getRecipient().getId(), true);

        chatMessage.setChatId(chatId.get());
        ChatMessage saved = chatMessageRepository.save(chatMessage);

        // Gửi tin nhắn tới đúng người nhận qua queue riêng của họ
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipient().getId().toString(),
                "/queue/messages",
                saved
        );
    }
}
