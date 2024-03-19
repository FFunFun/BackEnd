package com.ffuntree.ffunfun.exception.ffun;

public class FFunException extends RuntimeException {

    public FFunException(FFunErrorCode errorCode) {
        super(errorCode.message);
    }
}
