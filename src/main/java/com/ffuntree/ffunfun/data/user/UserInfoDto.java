package com.ffuntree.ffunfun.data.user;

import com.ffuntree.ffunfun.data.ffun.FFunRoomSimpleInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@Builder
public class UserInfoDto {

    private Long id;
    private String email;
    private String studentEmail;
    private String profileImage;
    private FFunRoomSimpleInfoDto ffunRoomInfo;

    public static UserInfoDto of(User user, String encodedProfileImageForBase64) {
        UserInfoDto build = UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .profileImage(encodedProfileImageForBase64)
                .studentEmail(user.getStudentEmail())
                .build();

        if (user.getFfunRoom() == null) {
            build.setFfunRoomInfo(null);
        }

        return build;
    }

}
