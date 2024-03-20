package com.ffuntree.ffunfun.ffunroom.exception;

import com.ffuntree.ffunfun.common.data.ErrorResponseDto;
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

    @ExceptionHandler(FFunPasswordWrong.class)
    public ResponseEntity<ErrorResponseDto> handleFFunPasswordWrongException(FFunPasswordWrong exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

}
