package umc.spring.service.reviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDto;

public interface ReviewCommandService {
    Review addReview(ReviewRequestDto.ReviewAddDto request);
}
