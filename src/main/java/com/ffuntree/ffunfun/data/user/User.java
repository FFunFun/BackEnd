package com.ffuntree.ffunfun.data.user;

import com.ffuntree.ffunfun.data.common.FileProperty;
import com.ffuntree.ffunfun.data.ffun.FFunRoom;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {

    // 데이터베이스의 primary key로 사용할 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    // 유저의 로그인에 사용할 uid (user id) -> email, password
    @Column(name = "email")
    private String email;

    private String password;

    @Column(name = "name")
    private String name;

    // 학교 인증에 사용할 학교 이메일
    @Column(name = "student_email")
    private String studentEmail;

    // 학번
    @Column(name = "student_number")
    private String studentNumber;

    // Enum 이름을 DB에 저장할 때 1, 2, 3 등 숫자가 아닌 재학/휴학 등 문자 형태로 저장
    @Enumerated(EnumType.STRING)
    @Column(name = "academic_status")
    private AcademicStatus academicStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @Column(name = "profile_image")
    private FileProperty profileImage;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ffun_room_id")
    private FFunRoom ffunRoom;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void joinFFun(FFunRoom ffunRoom) {
        this.ffunRoom = ffunRoom;
    }

}
