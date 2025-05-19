package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.memberMissionService.MemberMissionCommandService;
import umc.spring.service.missionService.MissionCommandService;
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
}
