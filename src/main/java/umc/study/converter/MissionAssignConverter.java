package umc.study.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MissionAssignment;
import umc.study.service.MemberSerivce.MemberService;
import umc.study.service.MissionService.MissionService;
import umc.study.web.dto.MissionChallengeRequestDTO;
import umc.study.web.dto.MissionChallengeResponseDTO;

@Component
@RequiredArgsConstructor
public class MissionAssignConverter {

    private final MemberService memberService;
    private final MissionService missionService;

    public MissionChallengeResponseDTO.MissionChallengeResultDTO toMissionChallengeResponseDTO(MissionAssignment missionAssignment) {
        return MissionChallengeResponseDTO.MissionChallengeResultDTO.builder()
                .memberName(missionAssignment.getMember().getName())
                .missionName(missionAssignment.getMission().getName())
                .build();
    }

    public MissionAssignment toMissionAssignment(MissionChallengeRequestDTO.AssignMissionDTO request) {
        String memberName = request.getMissionMember();
        Member member = memberService.findByName(memberName);

        Long missionId = request.getMissionId();
        Mission mission = missionService.findByMissionId(missionId);

        return MissionAssignment.builder()
                .member(member)
                .mission(mission)
                .build();
    }
}
