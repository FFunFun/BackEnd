package com.ffuntree.ffunfun.service;


import com.ffuntree.ffunfun.data.user.User;
import com.ffuntree.ffunfun.data.user.UserInfoDto;
import com.ffuntree.ffunfun.repository.UserRepository;
import com.ffuntree.ffunfun.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserInfoDto getUser(String accessToken) {
        User user = userRepository.findByEmail(getUsernameFromToken(accessToken)).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return UserInfoDto.of(user);
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenProvider.getAuthentication(token).getName();
    }

}
