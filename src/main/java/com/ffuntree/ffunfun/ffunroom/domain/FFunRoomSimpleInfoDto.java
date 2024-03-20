package com.ffuntree.ffunfun.ffunroom.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FFunRoomSimpleInfoDto {

    private Long ffunRoomId;

    private String name;

    private String description;

    public static FFunRoomSimpleInfoDto of(FFunRoom ffunRoom) {
        return new FFunRoomSimpleInfoDto(ffunRoom.getFfunRoomId(), ffunRoom.getName(), ffunRoom.getDescription());
    }

}
