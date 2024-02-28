package com.ffuntree.ffunfun.data.email;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idx", callSuper = false)
@Entity
@Table(name = "`MEMBER_AUTHENTICATION_CODE`")
@DynamicInsert
@DynamicUpdate
public class MemberAuthenticationCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", updatable = false)
    private Long idx;

    // 이메일
    @Column(name = "email", nullable = false)
    private String email;

    // 인증 코드
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    // 인증 여부
    @Column(name = "is_verified", nullable = false)
    private boolean isVerified;

    // 인증 유효 기간 설정
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "create_date", updatable = false, nullable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
}