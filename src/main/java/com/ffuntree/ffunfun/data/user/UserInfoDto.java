package com.ffuntree.ffunfun.data.user;

import com.ffuntree.ffunfun.data.ffun.FFunRoomSimpleInfoDto;
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
        return UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .profileImage(encodedProfileImageForBase64)
                .studentEmail(user.getStudentEmail())
                .ffunRoomInfo(FFunRoomSimpleInfoDto.of(user.getFfunRoom()))
                .build();
    }

}
