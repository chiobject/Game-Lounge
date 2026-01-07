package com.game_lounge.forge.domain.board.repository;

import com.game_lounge.forge.domain.board.entity.Board;
import com.game_lounge.forge.domain.board.entity.BoardCategory;
import com.game_lounge.forge.domain.board.entity.BoardStatus;
import com.game_lounge.forge.domain.lounge.entity.Lounge;
import com.game_lounge.forge.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 특정 라운지의 게시글 목록 (페이징)
    Page<Board> findByLounge(Lounge lounge, Pageable pageable);

    // 특정 라운지의 특정 상태 게시글 (페이징)
    Page<Board> findByLoungeAndStatus(Lounge lounge, BoardStatus status, Pageable pageable);

    // 특정 라운지의 고정 게시글
    List<Board> findByLoungeAndPinnedTrue(Lounge lounge);

    // 특정 라운지의 특정 카테고리 게시글 (페이징)
    Page<Board> findByLoungeAndCategory(Lounge lounge, BoardCategory category, Pageable pageable);

    // 특정 작성자의 게시글
    Page<Board> findByAuthor(User author, Pageable pageable);

    // 제목으로 검색
    Page<Board> findByLoungeAndTitleContaining(Lounge lounge, String keyword, Pageable pageable);
}
