package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberResponseDto;
import umc.spring.web.dto.MissionRequestDto;
import umc.spring.web.dto.MissionResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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


    public static MemberResponseDto.MissionPreviewDto missionPreviewDto(MemberMission memberMission){
        Mission mission = memberMission.getMission();

        return MemberResponseDto.MissionPreviewDto.builder()
                .description(mission.getDescription())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .status(memberMission.getStatus())
                .build();
    }

    public static MemberResponseDto.MissionPreviewListDto missionPreviewListDto(Page<MemberMission> missionList){
        List<MemberResponseDto.MissionPreviewDto> missionPreviewList = missionList.stream()
                .map(MemberMissionConverter::missionPreviewDto).collect(Collectors.toList());

        return MemberResponseDto.MissionPreviewListDto.builder()
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreviewList.size())
                .missionList(missionPreviewList)
                .build();
    }

    public static MissionResponseDto.CompleteMissionResultDto completeMissionDto(MemberMission memberMission){
        return MissionResponseDto.CompleteMissionResultDto.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .description(memberMission.getMission().getDescription())
                .completedAt(memberMission.getCompletedAt())
                .point(memberMission.getMission().getPoint())
                .status(memberMission.getStatus())
                .build();
    }
}
