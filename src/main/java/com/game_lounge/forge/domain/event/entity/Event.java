package com.game_lounge.forge.domain.event.entity;

import com.game_lounge.forge.domain.lounge.entity.Lounge;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EventType type;

    @Column(name = "title", nullable = false, length = 90)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EventStatus status;

    @Column(name = "open_at", nullable = false)
    private LocalDateTime openAt;

    @Column(name = "close_at", nullable = false)
    private LocalDateTime closeAt;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lounge_id", nullable = false)
    private Lounge lounge;

    public static Event create(EventType type, String title, String description, EventStatus status,
                               LocalDateTime openAt, LocalDateTime closeAt, Lounge lounge) {
        Event event = new Event();
        event.type = type;
        event.title = title;
        event.description = description;
        event.status = status;
        event.openAt = openAt;
        event.closeAt = closeAt;
        event.lounge = lounge;
        return event;
    }
}
