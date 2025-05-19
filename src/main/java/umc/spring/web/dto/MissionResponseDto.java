package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

public class MissionResponseDto {

    @AllArgsConstructor
    @Getter
    @Builder
    public static class AddMissionToStoreResultDto{
        Long missionId;
        Long storeId;
        LocalDateTime createdAt;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class ChallengeMissionResultDto{
        Long missionId;
        Long memberId;
        MissionStatus status;
        LocalDateTime createdAt;
    }
}
