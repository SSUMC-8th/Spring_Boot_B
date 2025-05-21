package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import umc.study.service.ShopService.ShopService;
import umc.study.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shops")
public class ShopRestController {

    private final ShopService shopService;
    private final ReviewService reviewService;
    private final MissionService missionService;

    //특정 지역에 가게 추가하기
    @PostMapping
    public ApiResponse<RegionShopResponseDTO.SaveShopResultDTO> save(@RequestBody RegionShopRequestDTO.SaveShopDTO request) {
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
    public ApiResponse<MissionResponseDTO.MissionResultDTO> saveMission(@RequestBody MissionRequestDTO.AddMissionDTO request,
                                                                        @PathVariable("shopId") Long shopId) {
        Shop shop = shopService.findById(shopId);
        Mission mission = missionService.joinMission(request, shop);

        return ApiResponse.onSuccess(MissionConverter.toMissionResultDTO(mission));
    }

}
