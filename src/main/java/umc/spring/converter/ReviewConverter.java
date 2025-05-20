package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDto;
import umc.spring.web.dto.ReviewResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewConverter {
    public static ReviewResponseDto.ReviewAddResultDto toReviewAddResultDto(Review review){
        return ReviewResponseDto.ReviewAddResultDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDto.ReviewAddDto request){
        return Review.builder()
                .content(request.getContent())
                .score(request.getScore())
                .reviewImageList(new ArrayList<>())
                .build();

    }
}
