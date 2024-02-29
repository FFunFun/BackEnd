package com.ffuntree.ffunfun.data.user;

import com.ffuntree.ffunfun.data.common.FileProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class SocialSignUpDto {

    private String email;

    private String name;

    private MultipartFile profileImage;

    public User toUser(String password, SocialType socialType, FileProperty profileImage) {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .academicStatus(AcademicStatus.ATTENDING)
                .profileImage(profileImage)
                .roles(List.of("USER"))
                .socialType(socialType)
                .build();
    }

}
