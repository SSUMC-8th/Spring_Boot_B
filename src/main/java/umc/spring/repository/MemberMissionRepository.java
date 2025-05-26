package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionId(Long memberId, Long MissionId);
    Page<MemberMission> findByMemberAndStatus (Member member, MissionStatus status, PageRequest pageRequest);
    Optional<MemberMission> findByMissionIdAndMemberId(Long memberId, Long missionId);
}
