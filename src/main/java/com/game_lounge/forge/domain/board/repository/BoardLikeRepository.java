package com.game_lounge.forge.domain.board.repository;

import com.game_lounge.forge.domain.board.entity.Board;
import com.game_lounge.forge.domain.board.entity.BoardLike;
import com.game_lounge.forge.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    // 특정 게시글에 특정 사용자가 좋아요 눌렀는지 찾기
    Optional<BoardLike> findByBoardAndUser(Board board, User user);

    // 이미 좋아요 눌렀는지 체크
    boolean existsByBoardAndUser(Board board, User user);

    // 특정 게시글의 좋아요 개수
    long countByBoard(Board board);

    // 특정 사용자가 누른 모든 좋아요
    long countByUser(User user);
}
