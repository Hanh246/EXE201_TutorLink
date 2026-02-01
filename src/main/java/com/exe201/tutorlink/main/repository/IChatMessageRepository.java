package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.main.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
