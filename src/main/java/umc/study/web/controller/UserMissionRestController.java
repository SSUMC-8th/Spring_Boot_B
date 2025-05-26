package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.UserMission;
import umc.study.dto.userMission.UserMissionRequestDTO;
import umc.study.dto.userMission.UserMissionResponseDTO;
import umc.study.service.UserMissionService.UserMissionCommandService;

@RestController
@RequestMapping("/api/v1/user-missions")
@RequiredArgsConstructor
@Tag(name = "UserMission", description = "사용자 미션 관련 API")
public class UserMissionRestController {

    private final UserMissionCommandService userMissionCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "미션 도전", description = "특정 미션에 도전합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "미션 도전 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "미션 또는 사용자 없음"),
            @ApiResponse(responseCode = "409", description = "이미 도전 중인 미션")
    })
    public umc.study.apiPayload.ApiResponse<UserMissionResponseDTO.JoinMissionResultDTO> joinMission(
            @RequestBody @Valid UserMissionRequestDTO.JoinMissionRequest request) {

        // 하드코딩된 사용자 ID 사용
        Long userId = 1L;

        UserMission userMission = userMissionCommandService.joinMission(request, userId);
        return umc.study.apiPayload.ApiResponse.onSuccess(UserMissionConverter.toJoinMissionResultDTO(userMission));
    }
}