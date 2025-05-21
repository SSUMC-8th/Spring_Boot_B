package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.domain.mapping.MissionAssignment;
import umc.study.service.MissionAssignmentService;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionRestController {

    private final MissionAssignmentService missionAssignmentService;

    //가게의 미션을 도전중인 미션에 추가하기
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.MissionChallengeResultDTO> challenge(@RequestBody @Valid MissionRequestDTO.AssignMissionDTO request,
                                                                               @PathVariable("missionId") Long missionId) {

        MissionAssignment missionAssignment = missionAssignmentService.challengeMission(request, missionId);

        return ApiResponse.onSuccess(MissionConverter.toMissionChallengeResponseDTO(missionAssignment));
    }
}
