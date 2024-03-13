package com.ffuntree.ffunfun.exception.ffun;

public class FFunAlreadyJoinedException extends FFunException {

    public FFunAlreadyJoinedException() {
        super(FFunErrorCode.FFUN_ALREADY_JOINED_USER);
    }
}
