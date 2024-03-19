package com.ffuntree.ffunfun.service;


import com.ffuntree.ffunfun.data.user.User;
import com.ffuntree.ffunfun.data.user.UserInfoDto;
import com.ffuntree.ffunfun.exception.user.UserNotFoundException;
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
    private final FileUploadService fileUploadService;

    public UserInfoDto getUser(String accessToken) {
        User user = userRepository.findByEmail(getUsernameFromToken(accessToken)).orElseThrow(UserNotFoundException::new);
        String encodedProfileImage = null;
        if (user.getProfileImage() != null) {
            encodedProfileImage = fileUploadService.loadFileAsBase64(user.getProfileImage().getFilename());
        }
        return UserInfoDto.of(user, encodedProfileImage);
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenProvider.getAuthentication(token).getName();
    }

    public void withdrawal(String accessToken) {
        User user = userRepository.findByEmail(getUsernameFromToken(accessToken)).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

}
