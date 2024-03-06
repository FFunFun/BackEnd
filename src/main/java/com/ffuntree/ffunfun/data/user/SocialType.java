package com.ffuntree.ffunfun.data.user;

import lombok.Getter;

@Getter
public enum SocialType {

    GOOGLE("GOOGLE"),
    NAVER("NAVER"),
    NONE("NONE");

    private final String type;

    SocialType(String type) {
        this.type = type;
    }
}
