package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.common.AuthenticatedUser;
import com.ffuntree.ffunfun.data.ffun.FFunRoomSimpleInfoDto;
import com.ffuntree.ffunfun.data.story.Story;
import com.ffuntree.ffunfun.data.story.StoryRegisterDto;
import com.ffuntree.ffunfun.data.story.StoryResponseDto;
import com.ffuntree.ffunfun.data.story.StoryUpdateDto;
import com.ffuntree.ffunfun.service.FFunService;
import com.ffuntree.ffunfun.service.StoryService;
import com.ffuntree.ffunfun.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stories")
public class StoryController {

    private final StoryService storyService;

    @PostMapping
    public void registerStory(@AuthenticationPrincipal AuthenticatedUser user, @RequestBody StoryRegisterDto storyRegisterDto) {
        storyService.registerStory(user.getEmail(), storyRegisterDto);
    }

    @GetMapping("/{storyId}")
    public ResponseEntity<StoryResponseDto> getStory(@PathVariable Long storyId) {
        return ResponseEntity.ok(storyService.getStory(storyId));
    }

    @DeleteMapping("/{storyId}")
    public void deleteStory(@PathVariable Long storyId) {
        storyService.deleteStory(storyId);
    }

    @PutMapping("/{storyId}")
    public void updateStory(@PathVariable Long storyId, @RequestBody StoryUpdateDto storyUpdateDto) {
        storyService.updateStory(storyId, storyUpdateDto);
    }

}
