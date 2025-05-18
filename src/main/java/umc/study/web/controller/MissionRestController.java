package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionAssignConverter;
import umc.study.domain.mapping.MissionAssignment;
import umc.study.service.MissionAssignmentService;
import umc.study.web.dto.MissionChallengeRequestDTO;
import umc.study.web.dto.MissionChallengeResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionAssignConverter missionAssignConverter;
    private final MissionAssignmentService missionAssignmentService;

    //가게의 미션을 도전중인 미션에 추가하기
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionChallengeResponseDTO.MissionChallengeResultDTO> challenge(@RequestBody @Valid MissionChallengeRequestDTO.AssignMissionDTO request,
                                                                                        @PathVariable("missionId") Long missionId) {
        MissionAssignment missionAssignment = missionAssignmentService.challengeMission(request);

        return ApiResponse.onSuccess(missionAssignConverter.toMissionChallengeResponseDTO(missionAssignment));
    }
}
