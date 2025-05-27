package umc.study.dto.userMission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.domain.enums.UserMissionStatus;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreviewListDTO {
        private List<UserMissionPreviewDTO> userMissionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreviewDTO {
        private Long userMissionId;
        private Long missionId;
        private String missionTitle;
        private String missionDescription;
        private String storeName;
        private String areaInfo;
        private Integer rewardAmount;
        private UserMissionStatus status;
        private LocalDateTime requestedAt;
        private LocalDateTime completedAt;
        private Boolean hasReview;
        private Integer pointsAwarded;
    }
}