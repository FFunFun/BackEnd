package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.user.UserInfoDto;
import com.ffuntree.ffunfun.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestHeader("Authorization") String accessToken) {
        return ResponseEntity.ok(userService.getUser(accessToken));
    }

}
