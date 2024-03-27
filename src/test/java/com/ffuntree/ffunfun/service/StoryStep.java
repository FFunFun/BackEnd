package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.story.StoryRegisterDto;
import com.ffuntree.ffunfun.data.user.UserSignInDto;
import com.ffuntree.ffunfun.data.user.UserSignUpDto;

public class StoryStep {

    static StoryRegisterDto 스토리_정보_생성1() {
        String title = "제목1";
        String content = "내용1";
        String datetime = "2021-08-01T00:00:00";
        return new StoryRegisterDto(title, content, datetime);
    }

    static StoryRegisterDto 스토리_정보_생성2() {
        String title = "제목2";
        String content = "내용2";
        String datetime = "2021-08-02T00:00:00";
        return new StoryRegisterDto(title, content, datetime);
    }
}