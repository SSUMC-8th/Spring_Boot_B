package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.UserMission;
import umc.study.domain.enums.UserMissionStatus;
import umc.study.dto.UserMissionDto;
import umc.study.dto.userMission.UserMissionResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserMissionConverter {

    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(UserMissionStatus.IN_PROGRESS)
                .requestedAt(LocalDateTime.now())
                .hasReview(false)
                .pointsAwarded(0)
                .build();
    }

    public static UserMissionResponseDTO.JoinMissionResultDTO toJoinMissionResultDTO(UserMission userMission) {
        return UserMissionResponseDTO.JoinMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .missionTitle(userMission.getMission().getTitle())
                .storeName(userMission.getMission().getStore().getName())
                .status(userMission.getStatus())
                .requestedAt(userMission.getRequestedAt())
                .createdAt(userMission.getCreatedAt())
                .build();
    }

    public static UserMissionResponseDTO.UserMissionPreviewDTO toUserMissionPreviewDTO(UserMission userMission) {
        Mission mission = userMission.getMission();
        String areaInfo = String.format("%s %s %s",
                mission.getArea().getProvince(),
                mission.getArea().getCity(),
                mission.getArea().getTown());

        return UserMissionResponseDTO.UserMissionPreviewDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(mission.getId())
                .missionTitle(mission.getTitle())
                .missionDescription(mission.getDescription())
                .storeName(mission.getStore().getName())
                .areaInfo(areaInfo)
                .rewardAmount(mission.getRewardAmount())
                .status(userMission.getStatus())
                .requestedAt(userMission.getRequestedAt())
                .completedAt(userMission.getCompletedAt())
                .hasReview(userMission.isHasReview())
                .pointsAwarded(userMission.getPointsAwarded())
                .build();
    }

    public static UserMissionResponseDTO.UserMissionPreviewDTO fromUserMissionDto(UserMissionDto dto) {
        // UserMissionDto에서 필요한 정보를 추출
        // 일부 정보는 UserMissionDto에 없을 수 있으므로 기본값 설정
        return UserMissionResponseDTO.UserMissionPreviewDTO.builder()
                .userMissionId(dto.getUmId())
                .missionId(dto.getMissionId())
                .missionTitle(dto.getMissionTitle())
                .missionDescription("") // UserMissionDto에 없는 필드
                .storeName(dto.getStoreName())
                .areaInfo("") // UserMissionDto에 없는 필드
                .rewardAmount(0) // UserMissionDto에 없는 필드
                .status(UserMissionStatus.valueOf(dto.getStatus()))
                .requestedAt(null) // UserMissionDto에 없는 필드
                .completedAt(null) // UserMissionDto에 없는 필드
                .hasReview(dto.getHasReview())
                .pointsAwarded(dto.getPointsAwarded())
                .build();
    }

    public static UserMissionResponseDTO.UserMissionPreviewListDTO fromUserMissionDtoList(
            List<UserMissionDto> userMissionDtos, int page, int pageSize) {

        List<UserMissionResponseDTO.UserMissionPreviewDTO> userMissionPreviewDTOList = userMissionDtos.stream()
                .map(UserMissionConverter::fromUserMissionDto)
                .collect(Collectors.toList());

        int totalElements = userMissionDtos.size();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        return UserMissionResponseDTO.UserMissionPreviewListDTO.builder()
                .userMissionList(userMissionPreviewDTOList)
                .listSize(userMissionPreviewDTOList.size())
                .totalPage(totalPages)
                .totalElements((long) totalElements)
                .isFirst(page == 0)
                .isLast(page >= totalPages - 1)
                .build();
    }

    public static UserMissionResponseDTO.UserMissionPreviewListDTO toUserMissionPreviewListDTO(Page<UserMission> userMissionPage) {
        List<UserMissionResponseDTO.UserMissionPreviewDTO> userMissionPreviewDTOList = userMissionPage.stream()
                .map(UserMissionConverter::toUserMissionPreviewDTO)
                .collect(Collectors.toList());

        return UserMissionResponseDTO.UserMissionPreviewListDTO.builder()
                .userMissionList(userMissionPreviewDTOList)
                .listSize(userMissionPreviewDTOList.size())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .isFirst(userMissionPage.isFirst())
                .isLast(userMissionPage.isLast())
                .build();
    }
}