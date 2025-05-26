package umc.study.converter;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import umc.study.domain.Area;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.dto.mission.MissionRequestDTO;
import umc.study.dto.mission.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.CreateMissionRequest request, Store store) {
        // 현재 스토어에 연결된 지역 정보 가져오기
        Area area = store.getArea();

        // 9자리 고유 코드 생성 (알파벳 대문자 + 숫자)
        String uniqueCode = generateUniqueCode();

        return Mission.builder()
                .store(store)
                .area(area)
                .title(request.getTitle())
                .description(request.getDescription())
                .condition(request.getCondition())
                .rewardType(request.getRewardType())
                .rewardAmount(request.getRewardAmount())
                .uniqueCode(uniqueCode)
                .minimumPurchaseAmount(request.getMinimumPurchaseAmount())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
    }

    public static MissionResponseDTO.CreateMissionResponse toCreateMissionResponse(Mission mission) {
        Store store = mission.getStore();
        Area area = mission.getArea();

        String areaFullName = area.getProvince() + " " + area.getCity() + " " + area.getTown();

        return MissionResponseDTO.CreateMissionResponse.builder()
                .missionId(mission.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .areaName(areaFullName)
                .title(mission.getTitle())
                .condition(mission.getCondition())
                .rewardType(mission.getRewardType())
                .rewardAmount(mission.getRewardAmount())
                .uniqueCode(mission.getUniqueCode())
                .minimumPurchaseAmount(mission.getMinimumPurchaseAmount())
                .startDate(mission.getStartDate())
                .endDate(mission.getEndDate())
                .status(mission.getStatus())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    // Mission -> MissionPreviewDTO 변환
    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        Store store = mission.getStore();
        Area area = mission.getArea();
        String areaFullName = area.getProvince() + " " + area.getCity() + " " + area.getTown();

        return MissionResponseDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .condition(mission.getCondition())
                .rewardType(mission.getRewardType())
                .rewardAmount(mission.getRewardAmount())
                .uniqueCode(mission.getUniqueCode())
                .minimumPurchaseAmount(mission.getMinimumPurchaseAmount())
                .startDate(mission.getStartDate())
                .endDate(mission.getEndDate())
                .status(mission.getStatus())
                .storeName(store.getName())
                .areaInfo(areaFullName)
                .build();
    }

    // Page<Mission> -> MissionPreviewListDTO 변환 (Stream 사용)
    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missionPage) {
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionPage.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .missionList(missionPreviewDTOList)
                .listSize(missionPreviewDTOList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    // 9자리 고유 코드 생성 (알파벳 대문자 + 숫자)
    private static String generateUniqueCode() {
        return RandomStringUtils.randomAlphanumeric(9).toUpperCase();
    }
}