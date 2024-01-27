package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.dto.UserSignUpDto;
import com.ffuntree.ffunfun.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/sign")
@RestController
public class SignController {

    private final SignService signService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpDto signUpDto) {
        signService.signUp(signUpDto);
        return "signUp success";
    }

}