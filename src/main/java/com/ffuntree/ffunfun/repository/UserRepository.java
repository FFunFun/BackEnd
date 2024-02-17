package com.ffuntree.ffunfun.repository;

import com.ffuntree.ffunfun.data.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUid(String uid);

    boolean existsByUid(String uid);

    boolean existsByUidAndPassword(String id, String password);
}
