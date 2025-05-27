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
import umc.study.converter.UserMissionConverter;
import umc.study.domain.UserMission;
import umc.study.dto.userMission.UserMissionRequestDTO;
import umc.study.dto.userMission.UserMissionResponseDTO;
import umc.study.service.UserMissionService.UserMissionCommandService;
import umc.study.service.UserMissionService.UserMissionQueryService;
import umc.study.validation.annotation.CheckPage;

@RestController
@RequestMapping("/api/v1/user-missions")
@RequiredArgsConstructor
@Validated
@Tag(name = "UserMission", description = "사용자 미션 관련 API")
public class UserMissionRestController {

    private final UserMissionCommandService userMissionCommandService;
    private final UserMissionQueryService userMissionQueryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "미션 도전", description = "특정 미션에 도전합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "미션 도전 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션 또는 사용자 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "이미 도전 중인 미션")
    })
    public umc.study.apiPayload.ApiResponse<UserMissionResponseDTO.JoinMissionResultDTO> joinMission(
            @RequestBody @Valid UserMissionRequestDTO.JoinMissionRequest request) {

        // 하드코딩된 사용자 ID 사용
        Long userId = 1L;

        UserMission userMission = userMissionCommandService.joinMission(request, userId);
        return umc.study.apiPayload.ApiResponse.onSuccess(UserMissionConverter.toJoinMissionResultDTO(userMission));
    }

    @GetMapping("/my")
    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "현재 사용자가 진행중인 미션 목록을 페이징으로 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 페이지 번호"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public umc.study.apiPayload.ApiResponse<UserMissionResponseDTO.UserMissionPreviewListDTO> getMyInProgressMissions(
            @RequestParam(name = "page") @CheckPage Integer page) {

        // 하드코딩된 사용자 ID 사용
        Long userId = 1L;

        // 프론트엔드는 1부터 시작하지만, JPA는 0부터 시작하므로 -1
        Page<UserMission> userMissionPage = userMissionQueryService.getMyInProgressMissions(userId, page - 1);

        return umc.study.apiPayload.ApiResponse.onSuccess(
                UserMissionConverter.toUserMissionPreviewListDTO(userMissionPage));
    }
}
