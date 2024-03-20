package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.ffunroom.domain.FFunRoomRegisterDto;

public class FFunStep {

    static FFunRoomRegisterDto 뻔_정보_생성() {
        String title = "뻔방";
        String description = "뻔한 방";
        String password = "1234";
        return new FFunRoomRegisterDto(title, description, password);
    }
}
