package com.ffuntree.ffunfun.exception.common;

import com.ffuntree.ffunfun.data.common.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.DateTimeException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<ErrorResponseDto> handleDateTimeException(DateTimeException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

}
