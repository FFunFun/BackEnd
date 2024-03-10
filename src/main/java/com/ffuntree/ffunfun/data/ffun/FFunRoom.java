package com.ffuntree.ffunfun.data.ffun;

import com.ffuntree.ffunfun.data.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class FFunRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String password;

    private boolean isDeleted;

    @OneToOne
    private User ffunManager;

}
