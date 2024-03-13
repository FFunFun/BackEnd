package com.ffuntree.ffunfun.data.ffun;

import com.ffuntree.ffunfun.data.user.UserSimpleInfoDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FFunRoomInfoDto {

    private Long ffunRoomId;

    private String name;

    private String description;

    private String password;

    private List<UserSimpleInfoDto> userinfo = new ArrayList<>();

    @Builder
    public FFunRoomInfoDto(Long ffunRoomId, String name, String description, String password) {
        this.ffunRoomId = ffunRoomId;
        this.name = name;
        this.description = description;
        this.password = password;
    }

    public static FFunRoomInfoDto of(FFunRoom ffunRoom) {
        FFunRoomInfoDto build = FFunRoomInfoDto.builder()
                .ffunRoomId(ffunRoom.getFfunRoomId())
                .name(ffunRoom.getName())
                .description(ffunRoom.getDescription())
                .password(ffunRoom.getPassword())
                .build();

        build.userinfo = ffunRoom.getFfunMembers().stream()
                .map(UserSimpleInfoDto::of)
                .collect(Collectors.toList());

        return build;
    }

}
