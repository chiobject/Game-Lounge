package com.game_lounge.domain.lounge.repository;

import com.game_lounge.domain.lounge.entity.Lounge;
import com.game_lounge.domain.lounge.entity.LoungeMember;
import com.game_lounge.domain.lounge.entity.MemberStatus;
import com.game_lounge.domain.lounge.entity.Role;
import com.game_lounge.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoungeMemberRepository extends JpaRepository<LoungeMember, Long> {

    // 특정 라운지의 특정 사용자 멤버십 찾기
    Optional<LoungeMember> findByLoungeAndUser(Lounge lounge, User user);

    // 라운지에 이미 가입되어 있는지 체크
    boolean existsByLoungeAndUser(Lounge lounge, User user);

    // 특정 라운지의 모든 멤버 조회
    List<LoungeMember> findByLounge(Lounge lounge);

    // 특정 사용자가 가입한 모든 라운지 멤버십
    List<LoungeMember> findByUser(User user);

    // 특정 라운지의 특정 역할 멤버들
    List<LoungeMember> findByLoungeAndRole(Lounge lounge, Role role);

    // 특정 라운지의 활성 멤버들만
    List<LoungeMember> findByLoungeAndStatus(Lounge lounge, MemberStatus status);
}
