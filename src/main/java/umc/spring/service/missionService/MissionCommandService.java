package umc.spring.service.missionService;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.MissionRequestDto;

public interface MissionCommandService {
    Mission addMission(MissionRequestDto.AddMissionToStoreDto request);
}
