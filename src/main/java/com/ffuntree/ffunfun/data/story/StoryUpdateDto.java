package com.ffuntree.ffunfun.data.story;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoryUpdateDto {

    private String title;
    private String content;
    private String datetime;
    private List<Long> participants;

}
