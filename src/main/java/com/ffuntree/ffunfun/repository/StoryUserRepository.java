package com.ffuntree.ffunfun.repository;

import com.ffuntree.ffunfun.data.story.Story;
import com.ffuntree.ffunfun.data.story.StoryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryUserRepository extends JpaRepository<StoryUser, Long> {
    void deleteAllByStory(Story story);
}