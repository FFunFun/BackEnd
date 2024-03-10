package com.ffuntree.ffunfun.data.ffun;

import com.ffuntree.ffunfun.data.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FFunRoomRegisterDto {

    private String name;

    private String description;

    private String password;

    public FFunRoom toEntity(User user) {
        return FFunRoom.builder()
                .name(name)
                .description(description)
                .password(password)
                .ffunManager(user)
                .build();
    }

}
