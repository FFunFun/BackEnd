package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.ffun.ExistUserDto;
import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import com.ffuntree.ffunfun.data.ffun.FFunRoomInfoDto;
import com.ffuntree.ffunfun.data.ffun.FFunRoomRegisterDto;
import com.ffuntree.ffunfun.data.user.User;
import com.ffuntree.ffunfun.exception.user.UserNotFoundException;
import com.ffuntree.ffunfun.repository.FFunRepository;
import com.ffuntree.ffunfun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FFunService {

    private final FFunRepository ffunRepository;
    private final UserRepository userRepository;

    @Transactional
    public FFunRoom makeFFunRoom(FFunRoomRegisterDto ffunRoomRegisterDto, String ffunManagerEmail) {
        User user = userRepository.findByEmail(ffunManagerEmail).orElseThrow(UserNotFoundException::new);
        checkDuplicateFFun(user);

        FFunRoom ffunRoom = ffunRepository.save(ffunRoomRegisterDto.toEntity());
        user.joinFFun(ffunRoom);
        user.getFfunRoom().getFfunMembers().add(user);

        return ffunRoom;
    }

    private void checkDuplicateFFun(User ffunManager) {
        // TODO 예외처리 구체화
        if (ffunManager.getFfunRoom() != null) {
            throw new IllegalStateException("이미 FFun에 가입된 사용자입니다.");
        }
    }

    @Transactional
    public FFunRoom joinFFun(String userEmail, Long ffunRoomId) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);

        // TODO 예외처리 구체화
        FFunRoom ffunRoom = ffunRepository.findById(ffunRoomId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 FFun입니다."));

        ffunRoom.joinUser(user);
        return ffunRoom;
    }

    public ExistUserDto isExistUser(Long ffunRoomId, String userEmail) {
        // TODO 예외처리 구체화
        FFunRoom ffunRoom = ffunRepository.findById(ffunRoomId).orElseThrow(() -> new IllegalArgumentException("방 없서"));
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        boolean isExistUser = ffunRoom.isExistUser(user);

        return new ExistUserDto(isExistUser);
    }

    public FFunRoomInfoDto getFFunRoomInfo(Long ffunRoomId) {
        FFunRoom ffunRoom = ffunRepository.findById(ffunRoomId).orElseThrow(() -> new IllegalArgumentException("방 없서"));
        return FFunRoomInfoDto.of(ffunRoom);
    }
}
