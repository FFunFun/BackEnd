package com.ffuntree.ffunfun.user.domain;

import com.ffuntree.ffunfun.common.data.FileProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserSignUpDto {

    private String email;

    private String name;

    private String password;

    private MultipartFile profileImage;

    public User toUser(SocialType socialType, FileProperty profileImage) {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .socialType(socialType)
                .academicStatus(AcademicStatus.ATTENDING)
                .profileImage(profileImage)
                .roles(List.of("USER"))
                .build();
    }

}
