package umc.study.converter;

import org.springframework.stereotype.Component;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Shop;
import umc.study.domain.mapping.MissionAssignment;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

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
                .build();
    }
}
