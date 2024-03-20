package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.ffunroom.FFunService;
import com.ffuntree.ffunfun.ffunroom.domain.FFunRoom;
import com.ffuntree.ffunfun.sign.SignService;
import com.ffuntree.ffunfun.user.UserService;
import com.ffuntree.ffunfun.user.domain.SocialType;
import com.ffuntree.ffunfun.user.domain.User;
import com.ffuntree.ffunfun.user.domain.UserSignUpDto;
import com.ffuntree.ffunfun.ffunroom.exception.FFunAlreadyJoinedException;
import com.ffuntree.ffunfun.ffunroom.exception.FFunPasswordWrong;
import com.ffuntree.ffunfun.ffunroom.FFunRepository;
import com.ffuntree.ffunfun.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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
    void 뻔_가입_성공() {
        // given
        UserSignUpDto userSignUpDto1 = SignStep.회원가입_정보_생성1();
        UserSignUpDto userSignUpDto2 = SignStep.회원가입_정보_생성2();

        User 방장 = signService.signUp(userSignUpDto1, SocialType.NONE);
        User 가입자 = signService.signUp(userSignUpDto2, SocialType.NONE);

        FFunRoom madeFFunRoom = ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 방장.getEmail());

        // when
        ffunService.joinFFun(가입자.getEmail(), madeFFunRoom.getFfunRoomId(), madeFFunRoom.getPassword());
        ffunRepository.flush();
        userRepository.flush();

        // then
        User 가입자_조회 = userRepository.findByEmail(가입자.getEmail()).get();
        assertThat(가입자_조회.getFfunRoom().getFfunRoomId()).isEqualTo(madeFFunRoom.getFfunRoomId());

        FFunRoom 뻔_방_조회 = ffunRepository.findById(madeFFunRoom.getFfunRoomId()).get();
        assertThat(뻔_방_조회.getFfunMembers().size()).isEqualTo(2);
    }

    @Test
    void 뻔_가입_실패_비밀번호_틀림() {
        // given
        UserSignUpDto userSignUpDto1 = SignStep.회원가입_정보_생성1();
        UserSignUpDto userSignUpDto2 = SignStep.회원가입_정보_생성2();

        User 방장 = signService.signUp(userSignUpDto1, SocialType.NONE);
        User 가입자 = signService.signUp(userSignUpDto2, SocialType.NONE);

        FFunRoom madeFFunRoom = ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 방장.getEmail());

        // when & then
        assertThatThrownBy(() -> ffunService.joinFFun(가입자.getEmail(),
                madeFFunRoom.getFfunRoomId(),
                "틀린비밀번호"))
                .isInstanceOf(FFunPasswordWrong.class);
    }

    @Test
    void 뻔_이미_가입됨() {
        // given
        UserSignUpDto userSignUpDto1 = SignStep.회원가입_정보_생성1();
        UserSignUpDto userSignUpDto2 = SignStep.회원가입_정보_생성2();
        UserSignUpDto userSignUpDto3 = SignStep.회원가입_정보_생성3();

        User 방장1 = signService.signUp(userSignUpDto1, SocialType.NONE);
        User 방장2 = signService.signUp(userSignUpDto2, SocialType.NONE);
        User 가입자 = signService.signUp(userSignUpDto3, SocialType.NONE);

        // when
        FFunRoom madeFFunRoom1 = ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 방장1.getEmail());
        FFunRoom madeFFunRoom2 = ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 방장2.getEmail());

        // then
        ffunService.joinFFun(가입자.getEmail(), madeFFunRoom1.getFfunRoomId(), madeFFunRoom1.getPassword());
        // 뻔이 이미 가입되어있는 상태에서 가입 시도
        assertThatThrownBy(() -> ffunService.joinFFun(가입자.getEmail(), madeFFunRoom2.getFfunRoomId(), madeFFunRoom2.getPassword()))
                .isInstanceOf(FFunAlreadyJoinedException.class);
    }

    @Test
    @Transactional
    void 뻔_탈퇴() {
        // given
        UserSignUpDto userSignUpDto1 = SignStep.회원가입_정보_생성1();
        UserSignUpDto userSignUpDto2 = SignStep.회원가입_정보_생성2();

        User 방장 = signService.signUp(userSignUpDto1, SocialType.NONE);
        User 가입자 = signService.signUp(userSignUpDto2, SocialType.NONE);

        FFunRoom madeFFunRoom = ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 방장.getEmail());
        ffunService.joinFFun(가입자.getEmail(), madeFFunRoom.getFfunRoomId(), madeFFunRoom.getPassword());

        // when
        ffunService.leaveFFun(가입자.getEmail());

        // then
        User 가입자_조회 = userRepository.findByEmail(가입자.getEmail()).get();
        assertThat(가입자_조회.getFfunRoom()).isNull();

        FFunRoom 뻔_방_조회 = ffunRepository.findById(madeFFunRoom.getFfunRoomId()).get();
        assertThat(뻔_방_조회.getFfunMembers().size()).isEqualTo(1);
    }

}