package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.dto.review.ReviewRequestDTO;
import umc.study.dto.review.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.CreateReviewRequest request, User user, Store store, Mission mission) {
        return Review.builder()
                .user(user)
                .store(store)
                .mission(mission)
                .rating(request.getRating())
                .content(request.getContent())
                .build();
    }

    public static ReviewResponseDTO.CreateReviewResponse toCreateReviewResponse(Review review) {
        return ReviewResponseDTO.CreateReviewResponse.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}