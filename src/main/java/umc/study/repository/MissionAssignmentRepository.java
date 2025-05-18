package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MissionAssignment;

import java.util.List;

public interface MissionAssignmentRepository extends JpaRepository<MissionAssignment, Long> {
    List<MissionAssignment> findMissionAssignmentsByMember(Member member);
    boolean existsMissionAssignmentByMemberAndMission(Member member, Mission mission);
}
