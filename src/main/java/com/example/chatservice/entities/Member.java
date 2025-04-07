package com.example.chatservice.entities;

import com.example.chatservice.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    String password;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String phoneNumber;
    LocalDate birthDay;
    String role;

    public void updatePassword(String password, String confirmPassword, PasswordEncoder passwordEncoder) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        this.password = passwordEncoder.encode(password);
    }

}
