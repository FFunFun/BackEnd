package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.ffun.ExistUserDto;
import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import com.ffuntree.ffunfun.data.ffun.FFunRoomInfoDto;
import com.ffuntree.ffunfun.data.ffun.FFunRoomRegisterDto;
import com.ffuntree.ffunfun.data.user.User;
import com.ffuntree.ffunfun.exception.ffun.FFunAlreadyJoinedException;
import com.ffuntree.ffunfun.exception.ffun.FFunNotFoundException;
import com.ffuntree.ffunfun.exception.ffun.FFunPasswordWrong;
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
        ffunRoom.joinUser(user);
        user.registerFFunManager();

        return ffunRoom;
    }

    private void checkDuplicateFFun(User user) {
        if (user.getFfunRoom() != null) {
            throw new FFunAlreadyJoinedException();
        }
    }

    @Transactional
    public void joinFFun(String userEmail, Long ffunRoomId, String password) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        FFunRoom ffunRoom = ffunRepository.findById(ffunRoomId).orElseThrow(FFunNotFoundException::new);
        if (!ffunRoom.getPassword().equals(password)) {
            throw new FFunPasswordWrong();
        }
        ffunRoom.joinUser(user);
    }

    public ExistUserDto isExistUser(Long ffunRoomId, String userEmail) {
        FFunRoom ffunRoom = ffunRepository.findById(ffunRoomId).orElseThrow(FFunNotFoundException::new);
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        boolean isExistUser = ffunRoom.isExistUser(user);

        return new ExistUserDto(isExistUser);
    }

    public FFunRoomInfoDto getFFunRoomInfo(Long ffunRoomId) {
        FFunRoom ffunRoom = ffunRepository.findById(ffunRoomId).orElseThrow(FFunNotFoundException::new);
        return FFunRoomInfoDto.of(ffunRoom);
    }
}
