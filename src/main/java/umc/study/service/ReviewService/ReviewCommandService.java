package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.dto.review.ReviewRequestDTO;

public interface ReviewCommandService {
    Review createReview(ReviewRequestDTO.CreateReviewRequest request, Long userId);
}