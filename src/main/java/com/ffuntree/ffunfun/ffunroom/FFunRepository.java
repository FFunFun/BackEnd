package com.ffuntree.ffunfun.ffunroom;

import com.ffuntree.ffunfun.ffunroom.domain.FFunRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FFunRepository extends JpaRepository<FFunRoom, Long> {
}
