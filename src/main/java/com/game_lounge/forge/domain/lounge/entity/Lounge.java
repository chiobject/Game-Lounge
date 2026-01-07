package com.game_lounge.forge.domain.lounge.entity;
import com.game_lounge.forge.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "lounges")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)

public class Lounge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lounge_id")
    private Long loungeId;

    @Column(name = "lounge_name", nullable = false, length = 30)
    private String loungeName;

    @Column(name = "slug", nullable = false, unique = true, length = 20)
    private String slug;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public static Lounge create(String loungeName, String slug, String description, User owner) {
        Lounge lounge = new Lounge();
        lounge.loungeName = loungeName;
        lounge.slug = slug;
        lounge.description = description;
        lounge.owner = owner;
        return lounge;
    }
}