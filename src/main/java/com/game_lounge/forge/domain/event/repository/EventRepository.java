package com.game_lounge.forge.domain.event.repository;

import com.game_lounge.forge.domain.event.entity.Event;
import com.game_lounge.forge.domain.event.entity.EventStatus;
import com.game_lounge.forge.domain.event.entity.EventType;
import com.game_lounge.forge.domain.lounge.entity.Lounge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // 특정 라운지의 이벤트 목록 (페이징)
    Page<Event> findByLounge(Lounge lounge, Pageable pageable);

    // 특정 라운지의 특정 상태 이벤트
    List<Event> findByLoungeAndStatus(Lounge lounge, EventStatus status);

    // 특정 라운지의 특정 타입 이벤트
    List<Event> findByLoungeAndType(Lounge lounge, EventType type);

    // 오픈 시각이 특정 시간 이전인 이벤트 (자동 오픈용)
    List<Event> findByStatusAndOpenAtBefore(EventStatus status, LocalDateTime now);

    // 종료 시각이 특정 시간 이전인 이벤트 (자동 종료용)
    List<Event> findByStatusAndCloseAtBefore(EventStatus status, LocalDateTime now);
}
