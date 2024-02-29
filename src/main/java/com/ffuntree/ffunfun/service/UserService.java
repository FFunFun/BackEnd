package com.ffuntree.ffunfun.service;


import com.ffuntree.ffunfun.data.user.User;
import com.ffuntree.ffunfun.data.user.UserInfoDto;
import com.ffuntree.ffunfun.repository.UserRepository;
import com.ffuntree.ffunfun.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final FileUploadService fileUploadService;

    public UserInfoDto getUser(String accessToken) {
        User user = userRepository.findByEmail(getUsernameFromToken(accessToken)).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        byte[] profileImage = null;
        if (user.getProfileImage() != null) {
            profileImage = fileUploadService.loadFileAsByteArray(user.getProfileImage().getFilename());
        }
        return UserInfoDto.of(user, profileImage);
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenProvider.getAuthentication(token).getName();
    }

}
