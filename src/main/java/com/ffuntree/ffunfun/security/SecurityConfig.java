package com.ffuntree.ffunfun.security;

import com.ffuntree.ffunfun.repository.UserRepository;
import com.ffuntree.ffunfun.service.CustomUserDetailsService;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

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
                        // 로그인, 회원가입 요청은 누구나 가능
                        .requestMatchers("api/v1/user/sign-in").permitAll()
                        .requestMatchers("api/v1/user/sign-up").permitAll()
                        .requestMatchers("api/v1/user/social/**").permitAll()
                        .requestMatchers("api/v1/user/social/google").permitAll()
                        .requestMatchers("test/test").permitAll()
                        .anyRequest().authenticated())

                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(new CustomUserDetailsService(passwordEncoder(), userRepository))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
