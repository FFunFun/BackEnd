package com.ffuntree.ffunfun.exception;

import com.ffuntree.ffunfun.data.dto.ErrorResponseDto;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthorizationExceptionHandler {

    @ExceptionHandler(AuthorizationServiceException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthenticationException(AuthorizationServiceException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponseDto> handleSignatureException(JwtException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }

}
