package com.example.chatservice.repositories;

import com.example.chatservice.entities.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

}
