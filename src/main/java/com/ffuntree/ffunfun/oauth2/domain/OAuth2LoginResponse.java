package com.ffuntree.ffunfun.oauth2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OAuth2LoginResponse {

    private boolean isExist;

}
