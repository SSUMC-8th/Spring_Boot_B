package umc.study.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.MissionConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MissionAssignment;
import umc.study.exception.handler.GeneralHandler;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionAssignmentRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionAssignmentService {

    private final MissionAssignmentRepository missionAssignmentRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    public MissionAssignment challengeMission(MissionRequestDTO.AssignMissionDTO request, Long missionId) {

        Member member = memberRepository.findById(request.getMissionMemberId())
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.MISSION_NOT_FOUND));

        MissionAssignment missionAssignment = MissionConverter.toMissionAssignment(request, member, mission);

        return missionAssignmentRepository.save(missionAssignment);
    }
}
