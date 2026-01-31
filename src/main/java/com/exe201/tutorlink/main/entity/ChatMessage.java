package com.exe201.tutorlink.main.entity;

import com.exe201.tutorlink.main.constants.MessageStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ChatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId; // Map với ChatRoom.chatId

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Users recipient;

    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();
    private MessageStatus status;
}
