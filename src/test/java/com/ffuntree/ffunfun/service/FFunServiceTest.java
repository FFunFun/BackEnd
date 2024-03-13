package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import com.ffuntree.ffunfun.data.user.SocialType;
import com.ffuntree.ffunfun.data.user.User;
import com.ffuntree.ffunfun.data.user.UserSignUpDto;
import com.ffuntree.ffunfun.repository.FFunRepository;
import com.ffuntree.ffunfun.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FFunServiceTest {

    @Autowired
    private FFunService ffunService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FFunRepository ffunRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SignService signService;

    @AfterEach
    void setUp() {
        ffunRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void 뻔_만들기() {
        // given
        UserSignUpDto userSignUpDto = SignStep.회원가입_정보_생성1();
        signService.signUp(userSignUpDto, SocialType.NONE);
        User user = userRepository.findByEmail(userSignUpDto.getEmail()).get();

        // when
        ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), user.getEmail());

        // then
        assertThat(ffunRepository.count()).isEqualTo(1);
    }

    @Test
    void 뻔_방장_확인() {
        // given
        UserSignUpDto userSignUpDto = SignStep.회원가입_정보_생성1();
        User 방장 = signService.signUp(userSignUpDto, SocialType.NONE);

        // when
        FFunRoom madeFFunRoom = ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 방장.getEmail());

        // then
        assertThat(madeFFunRoom.getFfunMembers().get(0).getEmail()).isEqualTo(방장.getEmail());
    }

    @Test
    @Transactional
    void 뻔_가입() {
        // given
        UserSignUpDto userSignUpDto1 = SignStep.회원가입_정보_생성1();
        UserSignUpDto userSignUpDto2 = SignStep.회원가입_정보_생성2();

        User 방장 = signService.signUp(userSignUpDto1, SocialType.NONE);
        User 가입자 = signService.signUp(userSignUpDto2, SocialType.NONE);

        FFunRoom madeFFunRoom = ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 방장.getEmail());

        // when
        ffunService.joinFFun(가입자.getEmail(), madeFFunRoom.getFfunRoomId());
        ffunRepository.flush();
        userRepository.flush();

        // then
        User 가입자_조회 = userRepository.findByEmail(가입자.getEmail()).get();
        assertThat(가입자_조회.getFfunRoom().getFfunRoomId()).isEqualTo(madeFFunRoom.getFfunRoomId());

        FFunRoom 뻔_방_조회 = ffunRepository.findById(madeFFunRoom.getFfunRoomId()).get();
        assertThat(뻔_방_조회.getFfunMembers().size()).isEqualTo(2);
    }

}