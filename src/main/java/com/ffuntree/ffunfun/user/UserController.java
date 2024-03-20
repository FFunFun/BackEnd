package com.ffuntree.ffunfun.user;

import com.ffuntree.ffunfun.sign.domain.AuthenticatedUser;
import com.ffuntree.ffunfun.user.domain.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> getUserInfo(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        return ResponseEntity.ok(userService.getUserInfo(authenticatedUser.getEmail()));
    }

    @DeleteMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        userService.withdrawal(authenticatedUser.getEmail());
        return ResponseEntity.ok("withdrawal success");
    }

}
