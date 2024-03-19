package com.ffuntree.ffunfun.exception.user;

public class UserEmailDuplicated extends UserException {

    public UserEmailDuplicated() {
        super(UserErrorCode.USER_EMAIL_DUPLICATED);
    }
}
