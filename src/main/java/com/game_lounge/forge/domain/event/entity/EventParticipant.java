package com.game_lounge.forge.domain.event.entity;

import com.game_lounge.forge.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_participants")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class EventParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_participant_id")
    private Long eventParticipantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private ParticipantResult result;

    @CreatedDate
    @Column(name = "participated_at", nullable = false, updatable = false)
    private LocalDateTime participatedAt;

    @Column(name = "reserved_at")
    private LocalDateTime reservedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static EventParticipant create(ParticipantResult result, LocalDateTime reservedAt,
                                         Event event, User user) {
        EventParticipant participant = new EventParticipant();
        participant.result = result;
        participant.reservedAt = reservedAt;
        participant.event = event;
        participant.user = user;
        return participant;
    }
}
