package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.common.TokenInfo;
import com.ffuntree.ffunfun.data.user.UserSignInDto;
import com.ffuntree.ffunfun.data.user.UserSignUpDto;
import com.ffuntree.ffunfun.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/sign-in")
    public ResponseEntity<TokenInfo> signIn(@RequestBody UserSignInDto userSignInDto) {
        TokenInfo tokenInfo = signService.signIn(userSignInDto);
        return ResponseEntity.ok(tokenInfo);
    }

}