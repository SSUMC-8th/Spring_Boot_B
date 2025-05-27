package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.AssignMission;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class AddMissionDTO {
        @NotBlank
        String missionName;
        @NotNull
        Integer missionPoint;
        LocalDateTime expirationDateTime;
    }

    @Getter
    @AssignMission
    public static class AssignMissionDTO {
        @NotNull
        Long missionMemberId;
        @NotNull
        Long missionId;
    }
}
