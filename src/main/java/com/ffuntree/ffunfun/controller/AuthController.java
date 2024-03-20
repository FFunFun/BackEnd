package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.email.ReqAuthenticateCodeDTO;
import com.ffuntree.ffunfun.data.email.ReqSendEmailAuthenticationDTO;
import com.ffuntree.ffunfun.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    // 이메일 인증 번호 요청
    @PostMapping("/email-authentication")
    public HttpEntity<?> sendEmailAuthentication(
            @RequestBody ReqSendEmailAuthenticationDTO reqSendEmailAuthenticationApiV1DTO) {
        return authService.sendEmailAuthentication(reqSendEmailAuthenticationApiV1DTO);
    }

    @PostMapping("/authentication-code")
    public HttpEntity<?> authenticateCode(@RequestBody ReqAuthenticateCodeDTO reqAuthenticateCodeApiV1DTO) {
        return authService.authenticateCode(reqAuthenticateCodeApiV1DTO);
    }
}