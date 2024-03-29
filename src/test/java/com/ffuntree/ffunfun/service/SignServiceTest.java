package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.common.TokenInfo;
import com.ffuntree.ffunfun.data.user.SocialType;
import com.ffuntree.ffunfun.data.user.User;
import com.ffuntree.ffunfun.data.user.UserSignInDto;
import com.ffuntree.ffunfun.data.user.UserSignUpDto;
import com.ffuntree.ffunfun.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SignServiceTest {

    @Autowired
    private SignService signService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void 회원가입() {
        // given
        UserSignUpDto dto = SignStep.회원가입_정보_생성1();

        // when
        User user = signService.signUp(dto, SocialType.NONE);

        // then
        assertThat(user.getEmail()).isEqualTo(dto.getEmail());
    }

    @Test
    void 로그인() {
        // given
        UserSignUpDto signUpDto = SignStep.회원가입_정보_생성1();
        UserSignInDto signInDto = SignStep.로그인_정보_생성(signUpDto);

        // when
        signService.signUp(signUpDto, SocialType.NONE);
        TokenInfo tokenInfo = signService.signIn(signInDto);

        // then
        assertThat(tokenInfo.getAccessToken()).isNotNull();
    }

}