package com.game_lounge.domain.board.repository;

import com.game_lounge.domain.board.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {

    // 카테고리 이름으로 찾기
    Optional<BoardCategory> findByCategoryName(String categoryName);

    // 카테고리 이름 중복 체크
    boolean existsByCategoryName(String categoryName);
}
