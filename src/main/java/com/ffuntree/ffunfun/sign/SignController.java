package com.ffuntree.ffunfun.sign;

import com.ffuntree.ffunfun.sign.domain.TokenInfo;
import com.ffuntree.ffunfun.user.domain.SocialType;
import com.ffuntree.ffunfun.user.domain.UserSignInDto;
import com.ffuntree.ffunfun.user.domain.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("api/v1/user")
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