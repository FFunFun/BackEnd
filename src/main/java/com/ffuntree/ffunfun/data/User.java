package com.ffuntree.ffunfun.data;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "student_email")
    private String studentEmail;

    @Column(name = "student_number")
    private String studentNumber;

    // Enum 이름을 DB에 저장할 때 1, 2, 3 등 숫자가 아닌 재학/휴학 등 문자 형태로 저장
    @Enumerated(EnumType.STRING)
    @Column(name = "academic_status")
    private AcademicStatus academicStatus;

    private String password;

    @Column(name = "profile_image")
    private String profileImage;

}
