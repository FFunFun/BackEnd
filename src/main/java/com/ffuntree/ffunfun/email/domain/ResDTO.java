package com.ffuntree.ffunfun.email.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResDTO<T> {
    private Integer code;
    private String message;
    private T data;
}