package com.ffuntree.ffunfun.data.story;

import com.ffuntree.ffunfun.data.ffun.FFunRoomSimpleInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryResponseDto {

    private Long id;

    private String title;

    private String content;

    private String datetime;

    private FFunRoomSimpleInfoDto ffunRoomInfo;

    private List<Long> participants;

    public static StoryResponseDto of(Story story) {
        return StoryResponseDto.builder()
                .id(story.getStoryId())
                .title(story.getTitle())
                .content(story.getContent())
                .datetime(story.getDatetime().toString())
                .ffunRoomInfo(FFunRoomSimpleInfoDto.of(story.getFfunRoom()))
                .participants(story.getStoryUsers()
                        .stream()
                        .map(storyUser -> storyUser.getUser().getId())
                        .toList())
                .build();
    }
}
