package com.ffuntree.ffunfun.data.oauth2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2RegisterResponse extends OAuth2LoginResponse {

    private OAuth2UserResourceDto oAuth2UserResourceDto;

    public OAuth2RegisterResponse(boolean isExist, OAuth2UserResourceDto oAuth2UserResourceDto) {
        super(isExist);
        this.oAuth2UserResourceDto = oAuth2UserResourceDto;
    }
}
