package com.ffuntree.ffunfun.data.dto;

import com.ffuntree.ffunfun.data.AcademicStatus;
import com.ffuntree.ffunfun.data.User;
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
                .roles(List.of("ROLE_USER"))
                .academicStatus(AcademicStatus.ATTENDING)
                .build();
    }

}
