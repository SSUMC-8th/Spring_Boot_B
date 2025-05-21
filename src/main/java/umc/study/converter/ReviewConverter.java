package umc.study.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Shop;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@Component
@RequiredArgsConstructor
public class ReviewConverter {

    public static ReviewResponseDTO.ReviewResultDTO toReviewResultDTO(Review review) {
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .reviewerId(review.getId())
                .shopId(review.getShop().getId())
                .reviewerName(review.getMember().getName())
                .shopName(review.getShop().getName())
                .reviewScore(review.getStar())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.WriteReviewDTO request, Member member, Shop shop) {
        return Review.builder()
                .member(member)
                .shop(shop)
                .reviewText(request.getReviewText())
                .star(request.getStar())
                .build();
    }
}
