package umc.study.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.enums.RewardType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionResponse {
        private Long missionId;
        private Long storeId;
        private String storeName;
        private String areaName;
        private String title;
        private String condition;
        private RewardType rewardType;
        private Integer rewardAmount;
        private String uniqueCode;
        private Integer minimumPurchaseAmount;
        private LocalDate startDate;
        private LocalDate endDate;
        private MissionStatus status;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewListDTO {
        private List<MissionPreviewDTO> missionList;
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
    public static class MissionPreviewDTO {
        private Long missionId;
        private String title;
        private String description;
        private String condition;
        private RewardType rewardType;
        private Integer rewardAmount;
        private String uniqueCode;
        private Integer minimumPurchaseAmount;
        private LocalDate startDate;
        private LocalDate endDate;
        private MissionStatus status;
        private String storeName;
        private String areaInfo; // "서울특별시 강남구 역삼동"
    }
}