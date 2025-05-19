package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
}
