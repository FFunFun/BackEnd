package com.ffuntree.ffunfun.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class SocialSignUpDto {

    private String email;

    private String name;

    private MultipartFile profileImage;

    public UserSignUpDto toUserSignUpDto(String password) {
        return UserSignUpDto.builder()
                .email(email)
                .name(name)
                .password(password)
                .profileImage(profileImage)
                .build();
    }
}
