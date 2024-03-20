package com.ffuntree.ffunfun.ffunroom.exception;

public class FFunAlreadyJoinedException extends FFunException {

    public FFunAlreadyJoinedException() {
        super(FFunErrorCode.FFUN_ALREADY_JOINED_USER);
    }
}
