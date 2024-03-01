package com.ffuntree.ffunfun.data.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.UrlResource;

@Data
@AllArgsConstructor
@Builder
public class UserInfoDto {

    private Long id;
    private String email;
    private String studentEmail;
    private String encodedProfileImageForBase64;

    public static UserInfoDto of(User user, String encodedProfileImageForBase64) {
        return UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .encodedProfileImageForBase64(encodedProfileImageForBase64)
                .studentEmail(user.getStudentEmail())
                .build();
    }

}
