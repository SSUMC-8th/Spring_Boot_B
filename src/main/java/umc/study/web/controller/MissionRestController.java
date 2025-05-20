package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.dto.mission.MissionRequestDTO;
import umc.study.dto.mission.MissionResponseDTO;
import umc.study.service.MissionService.MissionCommandService;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "미션 생성", description = "특정 가게에 새로운 미션을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "미션 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "가게 없음"),
            @ApiResponse(responseCode = "409", description = "이미 미션 존재")
    })
    public umc.study.apiPayload.ApiResponse<MissionResponseDTO.CreateMissionResponse> createMission(
            @RequestBody @Valid MissionRequestDTO.CreateMissionRequest request) {

        Mission createdMission = missionCommandService.createMission(request);
        return umc.study.apiPayload.ApiResponse.onSuccess(MissionConverter.toCreateMissionResponse(createdMission));
    }
}