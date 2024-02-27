package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.oauth2.OAuth2LoginResponse;
import com.ffuntree.ffunfun.data.user.SocialSignUpDto;
import com.ffuntree.ffunfun.data.user.SocialType;
import com.ffuntree.ffunfun.service.SocialLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/sign/social")
public class OAuth2Controller {

    private final SocialLoginService socialLoginService;

    @GetMapping("/google/sign-in")
    public ResponseEntity<OAuth2LoginResponse> googleLogin(@RequestParam("code") String code) {
        OAuth2LoginResponse oAuth2LoginResponse = socialLoginService.socialLogin(code);
        return ResponseEntity.ok(oAuth2LoginResponse);
    }

    @PostMapping("/google/sign-up")
    public void googleRegister(@RequestBody SocialSignUpDto socialSignUpDto) {
        socialLoginService.socialSignUp(socialSignUpDto, SocialType.GOOGLE);
    }

}