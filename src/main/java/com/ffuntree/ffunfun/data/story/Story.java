package com.ffuntree.ffunfun.data.story;

import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import com.ffuntree.ffunfun.data.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    private List<StoryUser> storyUsers = new ArrayList<>();

    public void update(StoryUpdateDto storyUpdateDto, List<User> participants) {
        this.title = storyUpdateDto.getTitle();
        this.content = storyUpdateDto.getContent();
        this.datetime = LocalDateTime.parse(storyUpdateDto.getDatetime());
        participants.forEach(this::addParticipant);
    }

    public void addParticipant(User user) {
        StoryUser storyUser = new StoryUser(user, this);
        storyUsers.add(storyUser);
    }

    @Builder
    public Story(String title, String content, LocalDateTime datetime, FFunRoom ffunRoom) {
        this.title = title;
        this.content = content;
        this.datetime = datetime;
        this.ffunRoom = ffunRoom;
    }

}