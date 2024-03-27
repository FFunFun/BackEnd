package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.story.StoryRegisterDto;
import com.ffuntree.ffunfun.data.story.StoryResponseDto;
import com.ffuntree.ffunfun.data.user.SocialType;
import com.ffuntree.ffunfun.data.user.UserSignUpDto;
import com.ffuntree.ffunfun.repository.FFunRepository;
import com.ffuntree.ffunfun.repository.StoryRepository;
import com.ffuntree.ffunfun.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class StoryServiceTest {

    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;
    @Autowired
    private FFunService ffunService;
    @Autowired
    private SignService signService;
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FFunRepository ffunRepository;

    @BeforeEach
    void setUp() {
        storyRepository.deleteAll();
        ffunRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Transactional
    @Test
    void 스토리_등록() {
        // given
        UserSignUpDto 회원가입_정보 = SignStep.회원가입_정보_생성1();
        signService.signUp(회원가입_정보, SocialType.NONE);
        ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 회원가입_정보.getEmail());

        // when
        StoryRegisterDto storyRegisterDto = StoryStep.스토리_정보_생성1();
        storyService.registerStory(회원가입_정보.getEmail(), storyRegisterDto);

        // then
        assertThat(storyRepository.count()).isEqualTo(1);
    }

    @Test
    @Transactional
    void 날짜_포맷_잘못됨() {
        // given
        UserSignUpDto 회원가입_정보 = SignStep.회원가입_정보_생성1();
        signService.signUp(회원가입_정보, SocialType.NONE);
        ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 회원가입_정보.getEmail());

        // when
        StoryRegisterDto storyRegisterDto = StoryRegisterDto.builder().title("제목").content("내용").datetime("20210101231232342343435").build();

        // then
        assertThrows(DateTimeParseException.class, () -> storyService.registerStory(회원가입_정보.getEmail(), storyRegisterDto));
    }

    @Test
    @Transactional
    void 스토리_확인() {
        // given
        UserSignUpDto 회원가입_정보 = SignStep.회원가입_정보_생성1();
        StoryStep.스토리_정보_생성1();
        signService.signUp(회원가입_정보, SocialType.NONE);
        ffunService.makeFFunRoom(FFunStep.뻔_정보_생성(), 회원가입_정보.getEmail());

        // when
        StoryRegisterDto storyRegisterDto = StoryStep.스토리_정보_생성1();
        Long savedStoryId = storyService.registerStory(회원가입_정보.getEmail(), storyRegisterDto);
        storyRepository.flush();

        // then
        StoryResponseDto response = storyService.getStory(savedStoryId);
        assertThat(response.getTitle()).isEqualTo(storyRegisterDto.getTitle());
        assertThat(response.getContent()).isEqualTo(storyRegisterDto.getContent());
        assertThat(LocalDateTime.parse(response.getDatetime())).isEqualTo(LocalDateTime.parse(storyRegisterDto.getDatetime()));
    }
}