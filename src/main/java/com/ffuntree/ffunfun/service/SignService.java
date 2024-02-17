package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.user.User;
import com.ffuntree.ffunfun.data.common.TokenInfo;
import com.ffuntree.ffunfun.data.user.UserSignInDto;
import com.ffuntree.ffunfun.data.user.UserSignUpDto;
import com.ffuntree.ffunfun.repository.UserRepository;
import com.ffuntree.ffunfun.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SignService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenInfo signIn(UserSignInDto userSignInDto) {
        log.info("[SignService] userSignInDto: {}", userSignInDto);

        String id = userSignInDto.getEmail();
        String password = userSignInDto.getPassword();

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        if (!authentication.isAuthenticated()) {
            throw new IllegalArgumentException("잘못된 ID/PW 입니다.");
        }

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }

    @Transactional
    public void signUp(UserSignUpDto signUpDto) {
        checkDuplicatedEmail(signUpDto.getEmail());
        User user = signUpDto.toUser();
        userRepository.save(user);
    }

    private void checkDuplicatedEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("duplicated email");
        }
    }

}
