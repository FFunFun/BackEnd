package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.story.StoryRegisterDto;
import com.ffuntree.ffunfun.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoryService {

    private final StoryRepository storyRepository;
    private final UserService userService;

    public void registerStory(StoryRegisterDto storyRegisterDto) {

    }
}
