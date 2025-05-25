package umc.spring.service.memberMissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionQueryService {
    Page<MemberMission> getMissionPage(Long memberId, MissionStatus status, Integer page);
}
