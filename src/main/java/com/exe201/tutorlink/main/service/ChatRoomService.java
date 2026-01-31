package com.exe201.tutorlink.main.service;

import com.exe201.tutorlink.main.entity.ChatRoom;
import com.exe201.tutorlink.main.entity.Users;
import com.exe201.tutorlink.main.repository.ChatRoomRepository;
import com.exe201.tutorlink.main.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository repository;
    @Autowired
    private IUserRepository userRepository;

    public Optional<String> getChatId(Long senderId, Long recipientId, boolean createIfNotExist) {
        return repository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(!createIfNotExist) return Optional.empty();
                    String chatId = String.format("%s_%s", senderId, recipientId);

                    Users sender = userRepository.findById(senderId)
                            .orElseThrow(() -> new RuntimeException("Sender not found"));
                    Users recipient = userRepository.findById(recipientId)
                            .orElseThrow(() -> new RuntimeException("Recipient not found"));
                    // Tạo 2 bản ghi cho cả 2 chiều để dễ query
                    ChatRoom senderRecipient = new ChatRoom(chatId, sender, recipient);
                    ChatRoom recipientSender = new ChatRoom(chatId, recipient, sender);

                    repository.save(senderRecipient);
                    repository.save(recipientSender);
                    return Optional.of(chatId);
                });
    }
}
