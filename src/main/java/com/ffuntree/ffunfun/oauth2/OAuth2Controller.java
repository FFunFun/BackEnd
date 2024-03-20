package com.ffuntree.ffunfun.oauth2;

import com.ffuntree.ffunfun.oauth2.domain.OAuth2LoginResponse;
import com.ffuntree.ffunfun.user.domain.SocialSignUpDto;
import com.ffuntree.ffunfun.user.domain.SocialType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user/social")
public class OAuth2Controller {

    private final SocialLoginService socialLoginService;

    @PostMapping("/google/sign-in")
    public ResponseEntity<OAuth2LoginResponse> googleLogin(@RequestParam("code") String code) {
        OAuth2LoginResponse oAuth2LoginResponse = socialLoginService.socialLogin(code);
        return ResponseEntity.ok(oAuth2LoginResponse);
    }

    @PostMapping("/google/sign-up")
    public String googleRegister(@RequestParam("email") String email,
                               @RequestParam("name") String name,
                               @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {
        SocialSignUpDto socialSignUpDto = new SocialSignUpDto(email, name, profileImage);
        socialLoginService.socialSignUp(socialSignUpDto, SocialType.GOOGLE);
        return "signUp success";
    }

}