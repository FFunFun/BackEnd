package com.ffuntree.ffunfun.sign.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticatedUser {

    private String email;
    private Collection<? extends GrantedAuthority> authorities;

}