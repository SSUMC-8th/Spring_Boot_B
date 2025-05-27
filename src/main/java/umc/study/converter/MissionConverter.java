package umc.study.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Shop;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MissionAssignment;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import java.util.List;

@Component
public class MissionConverter {

    public static MissionResponseDTO.MissionResultDTO toMissionResultDTO(Mission mission) {
        return MissionResponseDTO.MissionResultDTO.builder()
                .shopName(mission.getShop().getName())
                .missionName(mission.getName())
                .build();
    }

    public static MissionResponseDTO.MissionChallengeResultDTO toMissionChallengeResponseDTO(MissionAssignment missionAssignment) {
        return MissionResponseDTO.MissionChallengeResultDTO.builder()
                .memberName(missionAssignment.getMember().getName())
                .missionName(missionAssignment.getMission().getName())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.AddMissionDTO request, Shop shop) {
        return Mission.builder()
                .shop(shop)
                .name(request.getMissionName())
                .missionPoint(request.getMissionPoint())
                .expiredAt(request.getExpirationDateTime())
                .build();
    }

    public static MissionAssignment toMissionAssignment(MissionRequestDTO.AssignMissionDTO request, Member member, Mission mission) {
        return MissionAssignment.builder()
                .member(member)
                .mission(mission)
                .missionStatus(MissionStatus.PROGRESS)
                .build();
    }

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .missionName(mission.getName())
                .missionPoint(mission.getMissionPoint())
                .expiredAt(mission.getExpiredAt().toLocalDate())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missionList) {
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionList.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .toList();

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .missionList(missionPreviewDTOList)
                .build();
    }

    public static MissionResponseDTO.MissionAssignmentPreviewDTO toMissionAssignmentPreviewDTO(MissionAssignment missionAssignment) {
        return MissionResponseDTO.MissionAssignmentPreviewDTO.builder()
                .missionName(missionAssignment.getMission().getName())
                .missionPoint(missionAssignment.getMission().getMissionPoint())
                .build();
    }

    public static MissionResponseDTO.MissionAssignmentPreviewListDTO toMissionAssignmentPreviewListDTO(Page<MissionAssignment> missionAssignments) {
        List<MissionResponseDTO.MissionAssignmentPreviewDTO> missionAssignmentPreviewDTOList = missionAssignments.stream()
                .map(MissionConverter::toMissionAssignmentPreviewDTO)
                .toList();

        return MissionResponseDTO.MissionAssignmentPreviewListDTO.builder()
                .isFirst(missionAssignments.isFirst())
                .isLast(missionAssignments.isLast())
                .totalPage(missionAssignments.getTotalPages())
                .totalElements(missionAssignments.getTotalElements())
                .listSize(missionAssignmentPreviewDTOList.size())
                .missionList(missionAssignmentPreviewDTOList)
                .build();
    }

    public static MissionResponseDTO.MissionCompleteDTO toMissionCompleteDTO(MissionAssignment missionAssignment, Shop shop) {
        return MissionResponseDTO.MissionCompleteDTO.builder()
                .missionName(missionAssignment.getMission().getName())
                .shopName(shop.getName())
                .missionPoint(missionAssignment.getMission().getMissionPoint())
                .build();
    }
}
