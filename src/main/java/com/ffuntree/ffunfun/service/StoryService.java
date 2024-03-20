package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import com.ffuntree.ffunfun.data.story.Story;
import com.ffuntree.ffunfun.data.story.StoryRegisterDto;
import com.ffuntree.ffunfun.exception.ffun.FFunNotFoundException;
import com.ffuntree.ffunfun.exception.user.UserNotFoundException;
import com.ffuntree.ffunfun.repository.StoryRepository;
import com.ffuntree.ffunfun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoryService {

    private final StoryRepository storyRepository;
    private final UserRepository userRepository;

    public void registerStory(String email, StoryRegisterDto storyRegisterDto) {
        log.info("[StoryService] registerStory - email : {}", email);
        log.info("[registerStory] datetime : {}", storyRegisterDto.getDatetime());

        FFunRoom fFunRoom = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new).getFfunRoom();
        if (fFunRoom == null) {
            throw new FFunNotFoundException();
        }

        storyRepository.save(storyRegisterDto.toEntity(fFunRoom));
    }

    public void deleteStory(Long storyId) {
        storyRepository.deleteById(storyId);
    }

    public Story getStory(Long storyId) {
        // TODO : 예외 처리 구체화
        return storyRepository.findById(storyId).orElseThrow(() -> new RuntimeException("Story not found."));
    }
}
