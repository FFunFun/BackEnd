package com.ffuntree.ffunfun.exception.ffun;

import org.springframework.http.HttpStatus;

public enum FFunErrorCode {
    FFUN_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 뻔입니다."),
    FFUN_ALREADY_JOINED_USER(HttpStatus.BAD_REQUEST, "이미 뻔에 가입된 유저입니다.");

    final HttpStatus status;
    final String message;

    FFunErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
