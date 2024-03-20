package com.ffuntree.ffunfun.exception.story;

public class StoryNotFoundException extends StoryException {

    public StoryNotFoundException() {
        super(StoryErrorCode.STORY_NOT_FOUND);
    }
}
