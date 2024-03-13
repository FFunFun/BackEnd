package com.ffuntree.ffunfun.data.ffun;

import com.ffuntree.ffunfun.data.user.UserSimpleInfoDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FFunRoomInfoMemberDto {

    private Long ffunRoomId;

    private String name;

    private String description;

    private List<UserSimpleInfoDto> userinfo = new ArrayList<>();

    @Builder
    public FFunRoomInfoMemberDto(Long ffunRoomId, String name, String description) {
        this.ffunRoomId = ffunRoomId;
        this.name = name;
        this.description = description;
    }

    public static FFunRoomInfoMemberDto of(FFunRoom ffunRoom) {
        if(ffunRoom == null) {
            return null;
        }

        FFunRoomInfoMemberDto build = FFunRoomInfoMemberDto.builder()
                .ffunRoomId(ffunRoom.getFfunRoomId())
                .name(ffunRoom.getName())
                .description(ffunRoom.getDescription())
                .build();

        build.userinfo = ffunRoom.getFfunMembers().stream()
                .map(UserSimpleInfoDto::of)
                .collect(Collectors.toList());

        return build;
    }

}
