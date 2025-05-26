package umc.study.service.UserMissionService;

import umc.study.domain.UserMission;
import umc.study.dto.userMission.UserMissionRequestDTO;

public interface UserMissionCommandService {
    UserMission joinMission(UserMissionRequestDTO.JoinMissionRequest request, Long userId);
}