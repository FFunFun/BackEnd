package com.ffuntree.ffunfun.exception.ffun;

import com.ffuntree.ffunfun.exception.user.UserErrorCode;
import com.ffuntree.ffunfun.exception.user.UserException;

public class FFunNotFoundException extends FFunException {

    public FFunNotFoundException() {
        super(FFunErrorCode.FFUN_NOT_FOUND);
    }
}
