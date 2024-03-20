package com.ffuntree.ffunfun.user.domain;

import com.ffuntree.ffunfun.ffunroom.domain.FFunRoomSimpleInfoDto;
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
