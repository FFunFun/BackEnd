package com.ffuntree.ffunfun.exception.story;

public class StoryException extends RuntimeException {

    public StoryException(StoryErrorCode errorCode) {
        super(errorCode.message);
    }
}