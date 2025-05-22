package umc.study.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Shop;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.List;

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

    public static ReviewResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .reviewerName(review.getMember().getName())
                .reviewScore(review.getStar())
                .createdAt(review.getCreatedAt().toLocalDate())
                .context(review.getReviewText())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewList) {

        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewPreviewDTO).toList();

        return ReviewResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }
}
