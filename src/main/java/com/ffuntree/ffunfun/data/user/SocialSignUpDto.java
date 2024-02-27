package com.ffuntree.ffunfun.data.user;

import lombok.Getter;

import java.util.List;

@Getter
public class SocialSignUpDto {

    private String email;

    private String name;

    public User toUser(String password, SocialType socialType) {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .academicStatus(AcademicStatus.ATTENDING)
                .roles(List.of("USER"))
                .socialType(socialType)
                .build();
    }

}
