package com.ffuntree.ffunfun.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        /*
            Deprecated 된 요소들은 빨간줄이 쳐져 있을 수 있으나 사용은 가능
            ( 컴파일 에러 발생하지 않음 )
         */

        return httpSecurity
                // 토큰을 사용하는 방식이기 때문에 csrf 는 disable 처리
                .httpBasic().disable()
                .csrf().disable()

                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/sign/**").permitAll() // Sign 관련 요청은 누구나 접근 가능
                        .anyRequest().authenticated()) // 그 외의 요청은 인증된 회원만 접근 가능

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
