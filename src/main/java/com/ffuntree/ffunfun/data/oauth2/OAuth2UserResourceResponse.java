package com.ffuntree.ffunfun.data.oauth2;

import lombok.Getter;

@Getter
public class OAuth2UserResourceResponse {

    private String access_token;
    private String token_type;
    private String expires_in;
    private String score;
    private String id_token;

}
