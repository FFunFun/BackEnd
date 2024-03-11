package com.ffuntree.ffunfun.data.ffun;

import com.ffuntree.ffunfun.data.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class FFunRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ffun_room_id")
    private Long ffunRoomId;

    private String name;

    private String description;

    private String password;

    private boolean isDeleted;

    @OneToOne
    private User ffunManager;

    @OneToMany(mappedBy = "ffunRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> ffunMembers = new ArrayList<>();

    public boolean isExistUser(User user) {
        return ffunMembers.contains(user);
    }

    public void joinUser(User user) {
        ffunMembers.add(user);
        user.joinFFun(this);
    }
}
