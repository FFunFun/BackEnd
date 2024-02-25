package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.oauth2.OAuth2LoginResponse;
import com.ffuntree.ffunfun.service.SocialLoginService;
import jakarta.servlet.http.HttpServletResponse;
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

    @GetMapping("/google")
    public ResponseEntity<OAuth2LoginResponse> googleLogin(@RequestParam("code") String code) {
        log.info("code = {}", code);
        OAuth2LoginResponse oAuth2LoginResponse = socialLoginService.socialLogin(code);
        return ResponseEntity.ok(oAuth2LoginResponse);
    }

}
