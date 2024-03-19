package com.ffuntree.ffunfun.controller;

import com.ffuntree.ffunfun.data.ffun.FFunRoomSimpleInfoDto;
import com.ffuntree.ffunfun.data.story.StoryRegisterDto;
import com.ffuntree.ffunfun.service.FFunService;
import com.ffuntree.ffunfun.service.StoryService;
import com.ffuntree.ffunfun.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stories")
public class StoryController {

    private final StoryService storyService;

    @PostMapping
    public void registerStory(@RequestHeader("Authorization") String accessToken, StoryRegisterDto storyRegisterDto) {
        storyService.registerStory(storyRegisterDto);
    }


}
