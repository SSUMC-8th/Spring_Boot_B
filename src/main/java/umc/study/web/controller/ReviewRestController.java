package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.dto.review.ReviewRequestDTO;
import umc.study.dto.review.ReviewResponseDTO;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.ReviewService.ReviewQueryService;
import umc.study.validation.annotation.CheckPage;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Validated
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

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

    @GetMapping("/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "현재 사용자가 작성한 리뷰 목록을 페이징으로 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 페이지 번호"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public umc.study.apiPayload.ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getMyReviews(
            @RequestParam(name = "page") @CheckPage Integer page) {

        // 하드코딩된 사용자 ID 사용
        Long userId = 1L;

        // 프론트엔드는 1부터 시작하지만, JPA는 0부터 시작하므로 -1
        Page<Review> reviewPage = reviewQueryService.getMyReviews(userId, page - 1);

        return umc.study.apiPayload.ApiResponse.onSuccess(
                ReviewConverter.toReviewPreviewListDTO(reviewPage));
    }
}