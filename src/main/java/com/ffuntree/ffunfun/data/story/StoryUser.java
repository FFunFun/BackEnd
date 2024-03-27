package com.ffuntree.ffunfun.data.story;

import com.ffuntree.ffunfun.data.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "participant")
public class StoryUser {

    @Id
    @Column(name = "story_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storyUserId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    public StoryUser(User user, Story story) {
        this.user = user;
        this.story = story;
    }

}
