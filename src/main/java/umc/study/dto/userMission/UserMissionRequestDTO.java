package umc.study.dto.userMission;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistMission;
import umc.study.validation.annotation.NotInProgressMission;

public class UserMissionRequestDTO {

    @Getter
    public static class JoinMissionRequest {
        @NotNull(message = "미션 ID는 필수입니다")
        @ExistMission
        @NotInProgressMission
        private Long missionId;
    }
}