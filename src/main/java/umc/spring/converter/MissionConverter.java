package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDto;
import umc.spring.web.dto.MissionResponseDto;
import umc.spring.web.dto.StoreResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static StoreResponseDto.MissionPreviewDto missionPreviewDto(Mission mission){
        return StoreResponseDto.MissionPreviewDto.builder()
                .description(mission.getDescription())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static StoreResponseDto.MissionPreviewListDto missionPreviewListDto(Page<Mission> missionList){
        List<StoreResponseDto.MissionPreviewDto> missionPreviewList = missionList.stream()
                .map(MissionConverter::missionPreviewDto).collect(Collectors.toList());
        return StoreResponseDto.MissionPreviewListDto.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreviewList.size())
                .missionList(missionPreviewList)
                .build();
    }
}
