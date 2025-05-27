package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.dto.mission.MissionResponseDTO;
import umc.study.dto.store.StoreRequestDTO;
import umc.study.dto.store.StoreResponseDTO;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistStore;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
@Validated
@Tag(name = "Store", description = "가게 관련 API")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "가게 등록", description = "새로운 가게를 등록합니다.")
    public ApiResponse<StoreResponseDTO.CreateStoreResponse> createStore(
            @RequestBody @Valid StoreRequestDTO.CreateStoreRequest request,
            @RequestParam(required = false) Long ownerId) {

        // 실제 환경에서는 인증된 사용자 정보를 가져와야 하지만
        // 과제 조건에 따라 하드코딩된 사용자 ID 사용합니다잇~
        if (ownerId == null) {
            ownerId = 1L; // 하드코딩된 사용자 ID
        }

        Store createdStore = storeCommandService.createStore(request, ownerId);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreResponse(createdStore));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 활성화된 미션 목록을 페이징으로 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 페이지 번호"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게를 찾을 수 없음")
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getStoreMissions(
            @PathVariable(name = "storeId") @ExistStore Long storeId,
            @RequestParam(name = "page") @CheckPage Integer page) {

        // 프론트엔드는 1부터 시작하지만, JPA는 0부터 시작하므로 -1
        Page<Mission> missionPage = missionQueryService.getStoreMissions(storeId, page - 1);

        return ApiResponse.onSuccess(
                MissionConverter.toMissionPreviewListDTO(missionPage));
    }
}