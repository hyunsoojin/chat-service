package com.example.chatservice.services;

import com.example.chatservice.entities.Member;
import com.example.chatservice.enums.Gender;
import com.example.chatservice.enums.Role;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class MemberFactory {
    public static Member create(OAuth2UserRequest userRequest, OAuth2User oAuth2User){
        return switch (userRequest.getClientRegistration().getRegistrationId()){
            case "kakao" -> {
                Map<String, Object> attributeMap = oAuth2User.getAttribute("kakao_account");
                yield Member.builder()
                        .email((String)attributeMap.get("email"))
                        .nickName((String) ((Map)attributeMap.get("profile")).get("nickname"))
                        .name((String)attributeMap.get("name"))
                        .phoneNumber((String) attributeMap.get("phone_number"))
                        .gender(Gender.valueOf(((String)attributeMap.get("gender")).toUpperCase()))
                        .birthDay(getBirthDay(attributeMap))
                        .role(Role.USER.getCode())
                        .build();
            }
            case "google" -> {
                Map<String, Object> attributeMap = oAuth2User.getAttributes();
                yield Member.builder()
                        .email((String)attributeMap.get("email"))
                        .nickName((String) attributeMap.get("given_name"))
                        .name((String) attributeMap.get("name"))
                        .role(Role.USER.getCode())
                        .build();
            }
            default -> throw new IllegalArgumentException("연동되지 않은 서비스입니다.");
        };
    }

    private static LocalDate getBirthDay(Map<String, Object> attributeMap) {
        String birthyear = (String) attributeMap.get("birthyear");
        String birthday = (String) attributeMap.get("birthday");

        return LocalDate.parse(birthyear + birthday, DateTimeFormatter.BASIC_ISO_DATE);
    }
}
