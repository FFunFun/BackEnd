package com.ffuntree.ffunfun.exception.ffun;

import com.ffuntree.ffunfun.data.common.ErrorResponseDto;
import com.ffuntree.ffunfun.exception.user.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FFunExceptionHandler {

    @ExceptionHandler(FFunNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleFFunNotFoundException(FFunNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(FFunAlreadyJoinedException.class)
    public ResponseEntity<ErrorResponseDto> handleFFunAlreadyJoinedException(FFunAlreadyJoinedException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

}
