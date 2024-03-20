package com.ffuntree.ffunfun.exception.story;

import org.springframework.http.HttpStatus;

public enum StoryErrorCode {
    STORY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 스토리입니다.");

    final HttpStatus status;
    final String message;

    StoryErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
