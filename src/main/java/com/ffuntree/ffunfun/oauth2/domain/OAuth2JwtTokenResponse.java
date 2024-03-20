package com.ffuntree.ffunfun.oauth2.domain;

import com.ffuntree.ffunfun.sign.domain.TokenInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2JwtTokenResponse extends OAuth2LoginResponse {

    private TokenInfo tokenInfo;

    public OAuth2JwtTokenResponse(boolean isExist, TokenInfo tokenInfo) {
        super(isExist);
        this.tokenInfo = tokenInfo;
    }
}
