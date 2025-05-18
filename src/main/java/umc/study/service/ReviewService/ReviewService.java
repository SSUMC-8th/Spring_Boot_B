package umc.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.Shop;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;

    public Review joinReview(ReviewRequestDTO.WriteReviewDTO request, Shop shop) {
        Review review = reviewConverter.toReview(request, shop);

        return reviewRepository.save(review);
    }
}
