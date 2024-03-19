package com.ffuntree.ffunfun.repository;

import com.ffuntree.ffunfun.data.story.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
