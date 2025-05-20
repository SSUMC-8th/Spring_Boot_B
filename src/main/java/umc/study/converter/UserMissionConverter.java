package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.UserMission;
import umc.study.domain.enums.UserMissionStatus;
import umc.study.dto.userMission.UserMissionResponseDTO;

import java.time.LocalDateTime;

public class UserMissionConverter {

    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(UserMissionStatus.IN_PROGRESS)
                .requestedAt(LocalDateTime.now())
                .hasReview(false)
                .build();
    }

    public static UserMissionResponseDTO.JoinMissionResultDTO toJoinMissionResultDTO(UserMission userMission) {
        Mission mission = userMission.getMission();

        return UserMissionResponseDTO.JoinMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(mission.getId())
                .missionTitle(mission.getTitle())
                .storeName(mission.getStore().getName())
                .status(userMission.getStatus())
                .requestedAt(userMission.getRequestedAt())
                .createdAt(userMission.getCreatedAt())
                .build();
    }
}