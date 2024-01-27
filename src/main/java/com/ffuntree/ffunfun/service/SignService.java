package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.User;
import com.ffuntree.ffunfun.data.dto.UserSignUpDto;
import com.ffuntree.ffunfun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignService {

    private final UserRepository userRepository;


    public void signUp(UserSignUpDto signUpDto) {
        checkDuplicatedUid(signUpDto.getUid());
        User user = signUpDto.toUser();
        userRepository.save(user);
    }

    private void checkDuplicatedUid(String uid) {
        if (userRepository.existsByUid(uid)) {
            throw new RuntimeException("duplicated uid");
        }
    }
}
