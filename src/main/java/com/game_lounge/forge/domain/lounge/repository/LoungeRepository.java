package com.game_lounge.forge.domain.lounge.repository;

import com.game_lounge.forge.domain.lounge.entity.Lounge;
import com.game_lounge.forge.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoungeRepository extends JpaRepository<Lounge, Long> {

    // slug로 라운지 찾기 (URL용)
    Optional<Lounge> findBySlug(String slug);

    // slug 중복 체크
    boolean existsBySlug(String slug);

    // 특정 소유자의 라운지 목록
    List<Lounge> findByOwner(User owner);

    // 라운지 이름으로 검색
    List<Lounge> findByLoungeNameContaining(String keyword);
}
