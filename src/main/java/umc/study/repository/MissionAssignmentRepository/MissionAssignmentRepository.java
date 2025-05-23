package umc.study.repository.MissionAssignmentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.mapping.MissionAssignment;

public interface MissionAssignmentRepository extends JpaRepository<MissionAssignment, Long> {
    boolean existsMissionAssignmentByMemberIdAndMissionId(Long memberId, Long missionId);
    Page<MissionAssignment> findAllByMemberId(Long memberId, PageRequest pageRequest);
    MissionAssignment findMissionAssignmentByMemberIdAndMissionId(Long memberId, Long missionId);
}
