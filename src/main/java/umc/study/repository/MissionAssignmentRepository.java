package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.mapping.MissionAssignment;

public interface MissionAssignmentRepository extends JpaRepository<MissionAssignment, Long> {
    boolean existsMissionAssignmentByMemberIdAndMissionId(Long memberId, Long missionId);
}
