package com.ffuntree.ffunfun.data.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserInfoDto {

    private Long id;
    private String email;
    private String studentEmail;

    public static UserInfoDto of(User user) {
        return UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .studentEmail(user.getStudentEmail())
                .build();
    }

}
