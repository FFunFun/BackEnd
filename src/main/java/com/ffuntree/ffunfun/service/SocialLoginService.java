package com.ffuntree.ffunfun.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ffuntree.ffunfun.data.common.TokenInfo;
import com.ffuntree.ffunfun.data.oauth2.GoogleUserResourceDto;
import com.ffuntree.ffunfun.data.oauth2.OAuth2JwtTokenResponse;
import com.ffuntree.ffunfun.data.oauth2.OAuth2LoginResponse;
import com.ffuntree.ffunfun.data.oauth2.OAuth2RegisterResponse;
import com.ffuntree.ffunfun.data.user.UserSignInDto;
import com.ffuntree.ffunfun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class SocialLoginService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final UserRepository userRepository;
    private final SignService signService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.google.token-uri}")
    private String tokenUri;
    @Value("${spring.security.oauth2.client.registration.google.resource-uri}")
    private String resourceUri;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    public OAuth2LoginResponse socialLogin(String code) {
        String accessToken = getAccessToken(code);
        GoogleUserResourceDto userResource = getUserResource(accessToken);

        boolean isExistUser = checkExistUser(userResource);

        if (!isExistUser) {
            // 회원가입
            return new OAuth2RegisterResponse(false, userResource);
        }

        // 로그인
        String password = userRepository.findByEmail(userResource.getEmail()).get().getPassword();
        TokenInfo tokenInfo = signService.signIn(new UserSignInDto(userResource.getEmail(), password));

        return new OAuth2JwtTokenResponse(true, tokenInfo);
    }

    private boolean checkExistUser(GoogleUserResourceDto userResource) {
        return userRepository.existsByEmail(userResource.getEmail());
    }

    private String getAccessToken(String authorizationCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }

    private GoogleUserResourceDto getUserResource(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, GoogleUserResourceDto.class).getBody();
    }

}
