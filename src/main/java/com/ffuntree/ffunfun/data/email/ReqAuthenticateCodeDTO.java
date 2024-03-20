package com.ffuntree.ffunfun.data.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqAuthenticateCodeDTO {

    private String email;

    private String code;
}