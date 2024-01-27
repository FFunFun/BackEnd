package com.ffuntree.ffunfun.data.dto;

import com.ffuntree.ffunfun.data.User;
import lombok.Getter;

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
                .build();
    }

}
