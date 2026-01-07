package com.game_lounge.forge.domain.board.entity;

import com.game_lounge.forge.domain.lounge.entity.Lounge;
import com.game_lounge.forge.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "boards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "title", nullable = false, length = 90)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "pinned")
    private Boolean pinned;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BoardStatus status;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lounge_id", nullable = false)
    private Lounge lounge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private BoardCategory category;

    public static Board create(String title, String content, Boolean pinned, BoardStatus status,
                               Lounge lounge, User author, BoardCategory category) {
        Board board = new Board();
        board.title = title;
        board.content = content;
        board.pinned = pinned;
        board.status = status;
        board.lounge = lounge;
        board.author = author;
        board.category = category;
        return board;
    }
}
