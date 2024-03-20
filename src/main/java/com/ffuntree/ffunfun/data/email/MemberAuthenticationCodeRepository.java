package com.ffuntree.ffunfun.data.email;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;
public interface MemberAuthenticationCodeRepository extends JpaRepository<MemberAuthenticationCodeEntity, Long> {

    // 이메일로 end_date가 지금 이후고, delete_date가 null인 데이터 찾아오기
    Optional<MemberAuthenticationCodeEntity> findByEmailAndEndDateAfterAndDeleteDateIsNull(String email, LocalDateTime currentDateTime);

}
