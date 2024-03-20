package com.ffuntree.ffunfun.exception.story;

import com.ffuntree.ffunfun.data.common.ErrorResponseDto;
import com.ffuntree.ffunfun.exception.ffun.FFunAlreadyJoinedException;
import com.ffuntree.ffunfun.exception.ffun.FFunNotFoundException;
import com.ffuntree.ffunfun.exception.ffun.FFunPasswordWrong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StoryExceptionHandler {

    @ExceptionHandler(StoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleStoryNotFoundException(StoryNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

}
