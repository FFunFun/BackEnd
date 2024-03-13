package com.ffuntree.ffunfun.exception.user;

import org.springframework.http.HttpStatus;

public enum UserErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    USER_EMAIL_DUPLICATED(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");

    final HttpStatus status;
    final String message;

    UserErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
