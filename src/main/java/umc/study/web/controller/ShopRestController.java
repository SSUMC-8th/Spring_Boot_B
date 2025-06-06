package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.RegionShopConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Shop;
import umc.study.service.MissionService.MissionService;
import umc.study.service.ReviewService.ReviewService;
import umc.study.service.ShopService.ShopQueryService;
import umc.study.service.ShopService.ShopService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistShop;
import umc.study.web.dto.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/shops")
public class ShopRestController {

    private final ShopService shopService;
    private final ReviewService reviewService;
    private final MissionService missionService;
    private final ShopQueryService shopQueryService;

    //특정 지역에 가게 추가하기
    @PostMapping
    public ApiResponse<RegionShopResponseDTO.SaveShopResultDTO> save(@RequestBody @Valid RegionShopRequestDTO.SaveShopDTO request) {
        Shop shop = shopService.joinShop(request);
        return ApiResponse.onSuccess(RegionShopConverter.toSaveShopResultDTO(shop));
    }

    //가게에 리뷰 추가하기
    @PostMapping("/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewResultDTO> saveReview(@RequestBody @Valid ReviewRequestDTO.WriteReviewDTO request) {
        Review review = reviewService.joinReview(request);

        return ApiResponse.onSuccess(ReviewConverter.toReviewResultDTO(review));
    }

    //가게에 미션 추가하기
    @PostMapping("/{shopId}/missions")
    public ApiResponse<MissionResponseDTO.MissionResultDTO> saveMission(@RequestBody @Valid MissionRequestDTO.AddMissionDTO request,
                                                                        @PathVariable("shopId") Long shopId) {
        Shop shop = shopService.findById(shopId);
        Mission mission = missionService.joinMission(request, shop);

        return ApiResponse.onSuccess(MissionConverter.toMissionResultDTO(mission));
    }

    //리뷰 리스트 조회
    @GetMapping("/{shopId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. Query String 으로 Page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "shopId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getReviewList(@ExistShop @PathVariable(name = "shopId") Long shopId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = shopQueryService.getReviewList(shopId, --page);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPreviewListDTO(reviewList));
    }

    //특정 가게 미션 목록
    @GetMapping("/{shopId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회하는 API이며, 페이징을 포함합니다. Query String 으로 Page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "shopId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMissionList(@ExistShop @PathVariable(name = "shopId") Long shopId,
                                                                                @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Mission> missionList = missionService.getMissionListByShopId(shopId, --page);
        return ApiResponse.onSuccess(MissionConverter.toMissionPreviewListDTO(missionList));
    }
}
