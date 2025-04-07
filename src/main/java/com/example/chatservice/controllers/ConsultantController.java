package com.example.chatservice.controllers;

import com.example.chatservice.dtos.MemberDto;
import com.example.chatservice.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consultants")
@Slf4j
public class ConsultantController {

    private final CustomUserDetailsService customUserDetailsService;

    @ResponseBody
    @PostMapping
    public MemberDto saveMember(@RequestBody MemberDto memberDto){
        return customUserDetailsService.saveMember(memberDto);
    }

    @GetMapping
    public String index(){
        return "consultants/index.html";
    }

}
