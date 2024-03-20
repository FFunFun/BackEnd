package com.ffuntree.ffunfun.ffunroom.domain;

import com.ffuntree.ffunfun.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class FFunRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ffun_room_id")
    private Long ffunRoomId;

    private String name;

    private String description;

    private String password;

    @OneToMany(mappedBy = "ffunRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> ffunMembers = new ArrayList<>();

    private boolean isDeleted;

    @Builder
    public FFunRoom(String name, String description, String password, boolean isDeleted) {
        this.name = name;
        this.description = description;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public boolean isExistUser(User user) {
        return ffunMembers.contains(user);
    }

    public void joinUser(User user) {
        ffunMembers.add(user);
        user.joinFFun(this);
    }

    public void transferManager(User oldManager, User newManager) {
        oldManager.unregisterFFunManager();
        newManager.registerFFunManager();
    }

}
