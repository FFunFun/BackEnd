package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.common.TokenInfo;
import com.ffuntree.ffunfun.data.user.SocialSignUpDto;
import com.ffuntree.ffunfun.data.user.SocialType;
import com.ffuntree.ffunfun.data.user.UserSignInDto;
import com.ffuntree.ffunfun.data.user.UserSignUpDto;
import com.ffuntree.ffunfun.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("api/v1/sign")
@RestController
public class SignController {

    private final SignService signService;

    @PostMapping("/sign-up")
    public String signUp(@RequestParam("email") String email,
                         @RequestParam("name") String name,
                         @RequestParam("password") String password,
                         @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {
        UserSignUpDto socialSignUpDto = new UserSignUpDto(email, name, password, profileImage);
        signService.signUp(socialSignUpDto, SocialType.NONE);
        return "signUp success";
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenInfo> signIn(@RequestBody UserSignInDto userSignInDto) {
        TokenInfo tokenInfo = signService.signIn(userSignInDto);
        return ResponseEntity.ok(tokenInfo);
    }

}