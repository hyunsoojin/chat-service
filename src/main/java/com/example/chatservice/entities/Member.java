package com.example.chatservice.entities;

import com.example.chatservice.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @Id
    Long id;

    String email;
    String nickName;
    String name;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String phoneNumber;
    LocalDate birthDay;
    String role;

}
