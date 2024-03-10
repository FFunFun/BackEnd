package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.ffun.FFunRoomRegisterDto;
import com.ffuntree.ffunfun.data.user.UserInfoDto;
import com.ffuntree.ffunfun.service.FFunService;
import com.ffuntree.ffunfun.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/ffun")
@RestController
@RequiredArgsConstructor
public class FFunController {

    private final UserService userService;
    private final FFunService ffunService;

    @PostMapping
    public void registerFFun(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody FFunRoomRegisterDto ffunRoomRegisterDto) {
        String ffunManagerEmail = userService.getUsernameFromToken(accessToken);
        ffunService.makeFFunRoom(ffunRoomRegisterDto, ffunManagerEmail);
    }

}
