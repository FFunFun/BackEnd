package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import com.ffuntree.ffunfun.data.ffun.FFunRoomRegisterDto;
import com.ffuntree.ffunfun.data.user.User;
import com.ffuntree.ffunfun.exception.user.UserNotFoundException;
import com.ffuntree.ffunfun.repository.FFunRepository;
import com.ffuntree.ffunfun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FFunService {

    private final FFunRepository ffunRepository;
    private final UserRepository userRepository;

    public void makeFFunRoom(FFunRoomRegisterDto ffunRoomRegisterDto, String ffunManagerEmail) {
        User ffunManager = userRepository.findByEmail(ffunManagerEmail)
                .orElseThrow(UserNotFoundException::new);

        checkDuplicateFFun(ffunManager);

        ffunRepository.save(ffunRoomRegisterDto.toEntity(ffunManager));
    }

    private void checkDuplicateFFun(User ffunManager) {
        if (ffunManager.getFfunRoom() != null) {
            throw new IllegalStateException("이미 FFun에 가입된 사용자입니다.");
        }
    }

    public void joinFFun(String userEmail, Long ffunRoomId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);

        FFunRoom ffunRoom = ffunRepository.findById(ffunRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 FFun입니다."));

        user.joinFFun(ffunRoom);
        userRepository.save(user);
    }
}
