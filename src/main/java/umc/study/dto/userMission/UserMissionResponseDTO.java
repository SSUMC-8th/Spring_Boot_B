package umc.study.dto.userMission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.domain.enums.UserMissionStatus;

import java.time.LocalDateTime;

public class UserMissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinMissionResultDTO {
        private Long userMissionId;
        private Long missionId;
        private String missionTitle;
        private String storeName;
        private UserMissionStatus status;
        private LocalDateTime requestedAt;
        private LocalDateTime createdAt;
    }
}