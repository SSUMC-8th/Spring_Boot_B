package umc.study.dto.review;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.validation.annotation.ExistStore;

import java.math.BigDecimal;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewRequest {
        @NotNull(message = "가게 ID는 필수입니다")
        @ExistStore
        private Long storeId;

        private Long missionId; // 선택적 미션 연결

        @NotNull(message = "평점은 필수입니다")
        @DecimalMin(value = "0.0", message = "평점은 0.0 이상이어야 합니다")
        @DecimalMax(value = "5.0", message = "평점은 5.0 이하여야 합니다")
        private BigDecimal rating;

        @Size(min = 10, max = 1000, message = "리뷰 내용은 10자 이상 1000자 이하여야 합니다")
        private String content;
    }
}