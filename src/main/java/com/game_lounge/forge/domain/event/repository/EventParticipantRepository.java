package com.game_lounge.forge.domain.event.repository;

import com.game_lounge.forge.domain.event.entity.Event;
import com.game_lounge.forge.domain.event.entity.EventParticipant;
import com.game_lounge.forge.domain.event.entity.ParticipantResult;
import com.game_lounge.forge.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, Long> {

    // 특정 이벤트에 특정 사용자가 참여했는지 찾기
    Optional<EventParticipant> findByEventAndUser(Event event, User user);

    // 이미 참여했는지 체크 (중복 참여 방지)
    boolean existsByEventAndUser(Event event, User user);

    // 특정 이벤트의 모든 참여자
    List<EventParticipant> findByEvent(Event event);

    // 특정 이벤트의 특정 결과 참여자들 (당첨자만 등)
    List<EventParticipant> findByEventAndResult(Event event, ParticipantResult result);

    // 특정 사용자가 참여한 모든 이벤트
    List<EventParticipant> findByUser(User user);

    // 특정 이벤트의 참여자 수
    long countByEvent(Event event);

    // 특정 이벤트의 당첨자 수
    long countByEventAndResult(Event event, ParticipantResult result);
}
