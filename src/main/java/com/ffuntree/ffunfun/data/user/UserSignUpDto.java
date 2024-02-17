package com.ffuntree.ffunfun.data.user;

import lombok.Getter;

import java.util.List;

@Getter
public class UserSignUpDto {

    private String uid;

    private String name;

    private String password;

    public User toUser() {
        return User.builder()
                .uid(uid)
                .name(name)
                .password(password)
                .academicStatus(AcademicStatus.ATTENDING)
                .roles(List.of("USER"))
                .build();
    }

}
