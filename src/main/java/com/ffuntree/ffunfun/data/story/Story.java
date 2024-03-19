package com.ffuntree.ffunfun.data.story;

import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Story {

    @Id
    @Column(name = "story_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storyId;

    private String title;

    private String content;

    private LocalDateTime datetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ffun_room_id")
    private FFunRoom ffunRoom;

}