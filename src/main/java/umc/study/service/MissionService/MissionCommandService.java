package umc.study.service.MissionService;

import umc.study.domain.Mission;
import umc.study.dto.mission.MissionRequestDTO;

public interface MissionCommandService {
    Mission createMission(MissionRequestDTO.CreateMissionRequest request);
}