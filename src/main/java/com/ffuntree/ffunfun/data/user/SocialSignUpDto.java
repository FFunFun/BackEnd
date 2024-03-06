package com.ffuntree.ffunfun.data.user;

import com.ffuntree.ffunfun.data.common.FileProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
