package com.ffuntree.ffunfun.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInDto {

    private String email;

    private String password;

}
