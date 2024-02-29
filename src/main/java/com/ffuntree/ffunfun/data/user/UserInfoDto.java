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
    private byte[] profileImage;

    public static UserInfoDto of(User user, byte[] profileImage) {
        return UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .profileImage(profileImage)
                .studentEmail(user.getStudentEmail())
                .build();
    }

}
