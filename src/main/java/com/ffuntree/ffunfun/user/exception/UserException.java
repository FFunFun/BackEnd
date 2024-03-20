package com.ffuntree.ffunfun.user.exception;

public class UserException extends RuntimeException {

    public UserException(UserErrorCode errorCode) {
        super(errorCode.message);
    }
}
