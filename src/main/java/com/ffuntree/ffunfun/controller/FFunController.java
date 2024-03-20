package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.common.AuthenticatedUser;
import com.ffuntree.ffunfun.data.ffun.ExistUserDto;
import com.ffuntree.ffunfun.data.ffun.FFunRoomInfoMemberDto;
import com.ffuntree.ffunfun.data.ffun.FFunRoomRegisterDto;
import com.ffuntree.ffunfun.service.FFunService;
import com.ffuntree.ffunfun.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/ffun")
@RestController
@RequiredArgsConstructor
public class FFunController {

    private final FFunService ffunService;

    @PostMapping
    public void registerFFun(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @RequestBody FFunRoomRegisterDto ffunRoomRegisterDto) {
        String ffunManagerEmail = authenticatedUser.getEmail();
        ffunService.makeFFunRoom(ffunRoomRegisterDto, ffunManagerEmail);
    }

    @PostMapping("/join/{ffunRoomId}")
    public void joinFFun(
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser,
            @RequestParam String password,
            @PathVariable Long ffunRoomId) {
        String userEmail = authenticatedUser.getEmail();
        ffunService.joinFFun(userEmail, ffunRoomId, password);
    }

    @GetMapping("/exist/{ffunRoomId}")
    public ResponseEntity<ExistUserDto> existUser(
            @PathVariable Long ffunRoomId,
            @AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        String userEmail = authenticatedUser.getEmail();
        ExistUserDto existUser = ffunService.isExistUser(ffunRoomId, userEmail);

        return ResponseEntity.ok(existUser);
    }

    @GetMapping("/{ffunRoomId}")
    public ResponseEntity<FFunRoomInfoMemberDto> getFFunRoomInfo(@PathVariable Long ffunRoomId) {
        FFunRoomInfoMemberDto ffunRoomInfo = ffunService.getFFunRoomInfo(ffunRoomId);

        return ResponseEntity.ok(ffunRoomInfo);
    }

    @DeleteMapping("/leave")
    public void leaveFFun(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        String userEmail = authenticatedUser.getEmail();
        ffunService.leaveFFun(userEmail);
    }

}
