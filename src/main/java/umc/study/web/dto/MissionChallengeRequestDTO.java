package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.AssignMission;

public class MissionChallengeRequestDTO {

    @Getter
    @AssignMission
    public static class AssignMissionDTO {
        @NotBlank
        String missionMember;
        @NotNull
        Long missionId;
    }
}
