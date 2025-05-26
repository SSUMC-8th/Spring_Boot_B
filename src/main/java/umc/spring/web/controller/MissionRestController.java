package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.memberMissionService.MemberMissionCommandService;
import umc.spring.service.missionService.MissionCommandService;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.MemberResponseDto;
import umc.spring.web.dto.MissionRequestDto;
import umc.spring.web.dto.MissionResponseDto;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/add")
    public ApiResponse<MissionResponseDto.AddMissionToStoreResultDto> addMission(@RequestBody @Valid MissionRequestDto.AddMissionToStoreDto request){
        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.onSuccess(MissionConverter.toAddMissionToStoreResultDto(mission));
    }

    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDto.ChallengeMissionResultDto>
        challangeMission(@RequestBody @Valid MissionRequestDto.ChallengeMissionDto request){
        MemberMission memberMission = memberMissionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengeMissionResultDto(memberMission));
    }

    @PatchMapping("/complete")@Operation(summary = "진행중인 미션 진행 완료로 변경 API", description = "진행중인 미션을 진행 완료로 변경하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "해당 미션이 존재하지 않습니다", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "미션 완료 요청 바디",
            required = true,
            content = @Content(schema = @Schema(implementation = MissionRequestDto.CompleteMissionDto.class))
    )
    public ApiResponse<MissionResponseDto.CompleteMissionResultDto> setMissionComplete(@RequestBody @Valid MissionRequestDto.CompleteMissionDto request){

        MemberMission memberMission = memberMissionCommandService.setMissionComplete(request.getMissionId(), request.getMemberId());
        return ApiResponse.onSuccess(MemberMissionConverter.completeMissionDto(memberMission));
    }
}
