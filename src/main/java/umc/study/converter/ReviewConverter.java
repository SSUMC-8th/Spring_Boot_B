package umc.study.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Shop;
import umc.study.service.MemberSerivce.MemberService;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@Component
@RequiredArgsConstructor
public class ReviewConverter {

    private final MemberService memberService;

    public static ReviewResponseDTO.ReviewResultDTO toReviewResultDTO(Review review) {
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewerName(review.getMember().getName())
                .shopName(review.getShop().getName())
                .reviewScore(review.getStar())
                .build();
    }

    public Review toReview(ReviewRequestDTO.WriteReviewDTO request, Shop shop) {
        String reviewer = request.getReviewer();

        Member member = memberService.findByName(reviewer);

        return Review.builder()
                .member(member)
                .shop(shop)
                .reviewText(request.getReviewText())
                .star(request.getStar())
                .build();
    }
}
