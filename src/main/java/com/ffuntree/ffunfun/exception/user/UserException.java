package com.ffuntree.ffunfun.exception.user;

public class UserException extends RuntimeException {

    public UserException(UserErrorCode errorCode) {
        super(errorCode.message);
    }
}
