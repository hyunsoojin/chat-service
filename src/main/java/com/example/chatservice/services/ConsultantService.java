package com.example.chatservice.services;

import com.example.chatservice.dtos.ChatroomDto;
import com.example.chatservice.dtos.MemberDto;
import com.example.chatservice.entities.Chatroom;
import com.example.chatservice.entities.Member;
import com.example.chatservice.enums.Role;
import com.example.chatservice.repositories.ChatroomRepository;
import com.example.chatservice.repositories.MemberRepository;
import com.example.chatservice.vos.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsultantService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ChatroomRepository chatroomRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(username).get();

        if (Role.fromCode(member.getRole()) != Role.CONSULTANT){
            try {
                throw new AccessDeniedException("상담사가 아닙니다.");
            } catch (AccessDeniedException e) {
                throw new RuntimeException(e);
            }
        }

        return new CustomUserDetails(member, null);
    }

    public MemberDto saveMember(MemberDto memberDto) {
        Member member = MemberDto.to(memberDto);
        member.updatePassword(memberDto.password(), memberDto.confirmedPassword(), passwordEncoder);

        member = memberRepository.save(member);

        return MemberDto.from(member);
    }

    public Page<ChatroomDto> getAllChatrooms(Pageable pageable){
        Page<Chatroom> chatroomPage = chatroomRepository.findAll(pageable);
        return chatroomPage.map(ChatroomDto::from);
    }

}
