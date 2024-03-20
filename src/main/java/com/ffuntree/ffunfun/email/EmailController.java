package com.ffuntree.ffunfun.email;

import com.ffuntree.ffunfun.email.domain.ReqAuthenticateCodeDTO;
import com.ffuntree.ffunfun.email.domain.ReqSendEmailAuthenticationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService authService;
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