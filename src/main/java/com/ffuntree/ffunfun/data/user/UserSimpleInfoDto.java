package com.ffuntree.ffunfun.data.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserSimpleInfoDto {

    private Long id;
    private String email;
    private String studentEmail;

    public static UserSimpleInfoDto of(User user) {
        return UserSimpleInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .studentEmail(user.getStudentEmail())
                .build();
    }

}
