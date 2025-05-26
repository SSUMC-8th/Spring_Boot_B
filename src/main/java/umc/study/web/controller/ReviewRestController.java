package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Swagger 어노테이션
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.dto.review.ReviewRequestDTO;
import umc.study.dto.review.ReviewResponseDTO;
import umc.study.service.ReviewService.ReviewCommandService;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "리뷰 작성", description = "특정 가게에 리뷰를 작성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "리뷰 작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "가게 또는 미션 없음"),
            @ApiResponse(responseCode = "409", description = "이미 리뷰 존재")
    })
    public umc.study.apiPayload.ApiResponse<ReviewResponseDTO.CreateReviewResponse> createReview(
            @RequestBody @Valid ReviewRequestDTO.CreateReviewRequest request) {

        // 미션에 따라 하드코딩된 사용자 ID 사용
        Long userId = 1L; // 하드코딩된 사용자 ID

        Review createdReview = reviewCommandService.createReview(request, userId);
        return umc.study.apiPayload.ApiResponse.onSuccess(ReviewConverter.toCreateReviewResponse(createdReview));
    }
}