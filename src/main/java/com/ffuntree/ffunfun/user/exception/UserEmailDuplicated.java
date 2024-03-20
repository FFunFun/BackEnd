package com.ffuntree.ffunfun.user.exception;

public class UserEmailDuplicated extends UserException {

    public UserEmailDuplicated() {
        super(UserErrorCode.USER_EMAIL_DUPLICATED);
    }
}
