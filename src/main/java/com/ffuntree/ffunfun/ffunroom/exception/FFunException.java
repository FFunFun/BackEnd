package com.ffuntree.ffunfun.ffunroom.exception;

public class FFunException extends RuntimeException {

    public FFunException(FFunErrorCode errorCode) {
        super(errorCode.message);
    }
}
