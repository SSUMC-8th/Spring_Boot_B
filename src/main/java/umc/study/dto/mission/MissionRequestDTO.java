package umc.study.dto.mission;

import jakarta.validation.constraints.*;
import lombok.Getter;
import umc.study.domain.enums.RewardType;
import umc.study.validation.annotation.ExistStore;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class CreateMissionRequest {
        @NotNull(message = "가게 ID는 필수입니다")
        @ExistStore
        private Long storeId;

        @NotBlank(message = "미션 제목은 필수입니다")
        @Size(min = 5, max = 100, message = "미션 제목은 5자 이상 100자 이하여야 합니다")
        private String title;

        @Size(max = 1000, message = "미션 설명은 1000자 이하여야 합니다")
        private String description;

        @NotBlank(message = "미션 조건은 필수입니다")
        @Size(min = 5, max = 500, message = "미션 조건은 5자 이상 500자 이하여야 합니다")
        private String condition;

        @NotNull(message = "보상 유형은 필수입니다")
        private RewardType rewardType;

        @NotNull(message = "보상 금액은 필수입니다")
        @Min(value = 1, message = "보상 금액은 1 이상이어야 합니다")
        private Integer rewardAmount;

        @NotNull(message = "최소 구매 금액은 필수입니다")
        @Min(value = 0, message = "최소 구매 금액은 0 이상이어야 합니다")
        private Integer minimumPurchaseAmount;

        @NotNull(message = "시작일은 필수입니다")
        private LocalDate startDate;

        @NotNull(message = "종료일은 필수입니다")
        private LocalDate endDate;
    }
}