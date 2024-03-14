package com.ffuntree.ffunfun.exception;

import com.ffuntree.ffunfun.data.common.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestParamExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

}
