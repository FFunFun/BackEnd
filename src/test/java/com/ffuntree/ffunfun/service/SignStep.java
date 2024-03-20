package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.user.domain.UserSignInDto;
import com.ffuntree.ffunfun.user.domain.UserSignUpDto;

public class SignStep {

    static UserSignUpDto 회원가입_정보_생성1() {
        String email = "ffunfun1@funtree.com";
        String name = "김뻔뻔";
        String password = "1234";
        return new UserSignUpDto(email, name, password, null);
    }

    static UserSignUpDto 회원가입_정보_생성2() {
        String email = "ffunfun2@funtree.com";
        String name = "최뻔뻔";
        String password = "1234";
        return new UserSignUpDto(email, name, password, null);
    }

    static UserSignUpDto 회원가입_정보_생성3() {
        String email = "ffunfun3@funtree.com";
        String name = "이뻔뻔";
        String password = "1234";
        return new UserSignUpDto(email, name, password, null);
    }


    static UserSignInDto 로그인_정보_생성(UserSignUpDto signUpDto) {
        return new UserSignInDto(signUpDto.getEmail(), signUpDto.getPassword());
    }
}