package umc.spring.converter;

import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDto;
import umc.spring.web.dto.MissionResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissionConverter {

    public static MissionResponseDto.AddMissionToStoreResultDto toAddMissionToStoreResultDto(Mission mission){
        return MissionResponseDto.AddMissionToStoreResultDto.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDto.AddMissionToStoreDto request){
        return Mission.builder()
                .point(request.getPoint())
                .description(request.getDescription())
                .memberMissionList(new ArrayList<>())
                .deadline(request.getDeadline())
                .build();
    }
}
