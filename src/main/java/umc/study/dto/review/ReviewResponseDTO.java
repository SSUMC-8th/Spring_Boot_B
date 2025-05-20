package umc.study.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResponse {
        private Long reviewId;
        private Long storeId;
        private String storeName;
        private BigDecimal rating;
        private String content;
        private LocalDateTime createdAt;
    }
}