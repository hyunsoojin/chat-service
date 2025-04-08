package com.example.chatservice.controllers;

import com.example.chatservice.dtos.ChatroomDto;
import com.example.chatservice.dtos.MemberDto;
import com.example.chatservice.services.ConsultantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consultants")
@Slf4j
public class ConsultantController {

    private final ConsultantService consultantService;

    @ResponseBody
    @PostMapping
    public MemberDto saveMember(@RequestBody MemberDto memberDto){
        return consultantService.saveMember(memberDto);
    }

    @GetMapping
    public String index(){
        return "consultants/index.html";
    }

    @ResponseBody
    @GetMapping("/chats")
    public Page<ChatroomDto> getChatroomPage(Pageable pageable){
        return consultantService.getAllChatrooms(pageable);
    }

}
