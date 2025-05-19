package umc.spring.service.memberMissionService;

import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDto;

public interface MemberMissionCommandService {
    MemberMission challengeMission(MissionRequestDto.ChallengeMissionDto request);
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
