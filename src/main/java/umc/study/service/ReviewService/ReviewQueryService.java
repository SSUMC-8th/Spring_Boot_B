package umc.study.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;

public interface ReviewQueryService {

    // 내가 작성한 리뷰 목록 조회
    Page<Review> getMyReviews(Long userId, Integer page);
}