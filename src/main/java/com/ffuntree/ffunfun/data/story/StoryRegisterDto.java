package com.ffuntree.ffunfun.data.story;

import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import com.ffuntree.ffunfun.data.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class StoryRegisterDto {

    private String title;
    private String content;
    private String datetime;
    private List<Long> participants = new ArrayList<>();

    @Builder
    public StoryRegisterDto(String title, String content, String datetime) {
        this.title = title;
        this.content = content;
        this.datetime = datetime;
    }

    public Story toEntity(FFunRoom fFunRoom, List<User> participants) {
        Story story = Story.builder()
                .ffunRoom(fFunRoom)
                .title(title)
                .content(content)
                .datetime(LocalDateTime.parse(datetime))
                .build();

        participants.forEach(story::addParticipant);
        return story;
    }

}
