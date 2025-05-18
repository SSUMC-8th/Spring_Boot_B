package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

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
}
