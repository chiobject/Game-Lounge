package com.game_lounge.forge.domain.board.repository;

import com.game_lounge.forge.domain.board.entity.Board;
import com.game_lounge.forge.domain.board.entity.BoardComment;
import com.game_lounge.forge.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    // 특정 게시글의 모든 댓글
    List<BoardComment> findByBoard(Board board);

    // 특정 게시글의 최상위 댓글만 (대댓글 제외)
    List<BoardComment> findByBoardAndParentCommentIsNull(Board board);

    // 특정 댓글의 대댓글들
    List<BoardComment> findByParentComment(BoardComment parentComment);

    // 특정 작성자의 댓글
    List<BoardComment> findByAuthor(User author);

    // 특정 게시글의 댓글 개수
    long countByBoard(Board board);
}
