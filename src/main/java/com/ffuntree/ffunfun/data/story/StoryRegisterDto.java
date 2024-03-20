package com.ffuntree.ffunfun.data.story;

import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoryRegisterDto {

    private String title;
    private String content;
    private String datetime;

    public Story toEntity(FFunRoom fFunRoom) {
        return Story.builder()
                .ffunRoom(fFunRoom)
                .title(title)
                .content(content)
                .datetime(LocalDateTime.parse(datetime))
                .build();
    }

}
