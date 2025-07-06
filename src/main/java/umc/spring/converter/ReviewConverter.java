package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.MemberResponseDto;
import umc.spring.web.dto.ReviewRequestDto;
import umc.spring.web.dto.ReviewResponseDto;
import umc.spring.web.dto.StoreResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static StoreResponseDto.ReviewPreviewDto reviewPreviewDto(Review review){
        return StoreResponseDto.ReviewPreviewDto.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }
    public static StoreResponseDto.ReviewPreviewListDto reviewPreviewListDto(Page<Review> reviewList){
        List<StoreResponseDto.ReviewPreviewDto> reviewPreviewDtoList = reviewList.stream().
                map(ReviewConverter::reviewPreviewDto).collect(Collectors.toList());

        return StoreResponseDto.ReviewPreviewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDtoList.size())
                .reviewList(reviewPreviewDtoList)
                .build();
    }

}
