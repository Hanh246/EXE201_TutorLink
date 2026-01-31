package com.exe201.tutorlink.main.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ChatRoom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Users recipient;

    public ChatRoom(String chatId, Users sender, Users recipient) {
        this.chatId = chatId;
        this.sender = sender;
        this.recipient = recipient;
    }
}
