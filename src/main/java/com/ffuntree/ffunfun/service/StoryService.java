package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import com.ffuntree.ffunfun.data.story.Story;
import com.ffuntree.ffunfun.data.story.StoryRegisterDto;
import com.ffuntree.ffunfun.data.story.StoryResponseDto;
import com.ffuntree.ffunfun.data.story.StoryUpdateDto;
import com.ffuntree.ffunfun.exception.ffun.FFunNotFoundException;
import com.ffuntree.ffunfun.exception.story.StoryNotFoundException;
import com.ffuntree.ffunfun.exception.user.UserNotFoundException;
import com.ffuntree.ffunfun.repository.StoryRepository;
import com.ffuntree.ffunfun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoryService {

    private final StoryRepository storyRepository;
    private final UserRepository userRepository;

    public Long registerStory(String email, StoryRegisterDto storyRegisterDto) {
        log.info("[StoryService] registerStory - email : {}", email);
        log.info("[registerStory] datetime : {}", storyRegisterDto.getDatetime());

        FFunRoom fFunRoom = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new).getFfunRoom();
        if (fFunRoom == null) {
            throw new FFunNotFoundException();
        }

        Story savedStory = storyRepository.save(storyRegisterDto.toEntity(fFunRoom));
        return savedStory.getStoryId();
    }

    public void deleteStory(Long storyId) {
        // 스토리 삭제 체크
        if (!storyRepository.existsById(storyId)) {
            throw new StoryNotFoundException();
        }
        storyRepository.deleteById(storyId);
    }

    public StoryResponseDto getStory(Long storyId) {
        return StoryResponseDto.of(storyRepository.findById(storyId).orElseThrow(StoryNotFoundException::new));
    }

    @Transactional
    public void updateStory(Long storyId, StoryUpdateDto storyUpdateDto) {
        Story story = storyRepository.findById(storyId).orElseThrow(StoryNotFoundException::new);
        story.update(storyUpdateDto);
    }
}
