package com.game_lounge.forge.domain.user.repository;

import com.game_lounge.forge.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // username으로 사용자 찾기
    Optional<User> findByUserName(String userName);

    // email로 사용자 찾기
    Optional<User> findByEmail(String email);

    // username 중복 체크
    boolean existsByUserName(String userName);

    // email 중복 체크
    boolean existsByEmail(String email);
}
