package com.ffuntree.ffunfun.ffunroom.exception;

public class FFunNotFoundException extends FFunException {

    public FFunNotFoundException() {
        super(FFunErrorCode.FFUN_NOT_FOUND);
    }
}
