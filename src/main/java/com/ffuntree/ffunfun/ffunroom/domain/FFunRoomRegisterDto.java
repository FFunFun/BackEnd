package com.ffuntree.ffunfun.ffunroom.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FFunRoomRegisterDto {

    private String name;

    private String description;

    private String password;

    public FFunRoom toEntity() {
        return FFunRoom.builder()
                .name(name)
                .description(description)
                .password(password)
                .build();
    }

}
