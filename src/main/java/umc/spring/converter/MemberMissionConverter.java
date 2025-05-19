package umc.spring.converter;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDto;
import umc.spring.web.dto.MissionResponseDto;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MissionResponseDto.ChallengeMissionResultDto toChallengeMissionResultDto(MemberMission memberMission){
        return MissionResponseDto.ChallengeMissionResultDto.builder()
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(MissionRequestDto.ChallengeMissionDto request){
        return MemberMission.builder()
                .status(MissionStatus.ONGOING)
                .build();
    }
}
