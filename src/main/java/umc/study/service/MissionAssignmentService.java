package umc.study.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.MissionAssignConverter;
import umc.study.domain.Member;
import umc.study.domain.mapping.MissionAssignment;
import umc.study.exception.handler.GeneralHandler;
import umc.study.repository.MissionAssignmentRepository;
import umc.study.web.dto.MissionChallengeRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionAssignmentService {

    private final MissionAssignmentRepository missionAssignmentRepository;
    private final MissionAssignConverter missionAssignConverter;

    public MissionAssignment challengeMission(MissionChallengeRequestDTO.AssignMissionDTO request) {
        MissionAssignment missionAssignment = missionAssignConverter.toMissionAssignment(request);
        Member member = missionAssignment.getMember();

        List<MissionAssignment> assignments = missionAssignmentRepository.findMissionAssignmentsByMember(member);

        for (MissionAssignment assignment : assignments) {
            if (assignment.getMission().getId().equals(missionAssignment.getMission().getId())) {
                throw new GeneralHandler(ErrorStatus.MISSION_ALREADY_ASSIGNED);
            }
        }

        return missionAssignmentRepository.save(missionAssignment);
    }
}
