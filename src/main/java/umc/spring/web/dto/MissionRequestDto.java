package umc.spring.web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.NotAlreadyChallenged;

import java.time.LocalDate;

public class MissionRequestDto {

    @Getter
    public static class AddMissionToStoreDto{
        @NotNull
        Long storeId;
        @NotBlank(message = "설명을 입력해주세요.")
        String description;
        @NotNull(message = "포인트를 입력해주세요")
        Integer point;
        @Future
        LocalDate deadline;
    }

    @Getter
    @NotAlreadyChallenged
    public static class ChallengeMissionDto{
        @NotNull
        Long missionId;
        @NotNull
        Long memberId;
    }

    @Getter
    public static class CompleteMissionDto{
        @NotNull
        Long memberId;
        @NotNull
        Long missionId;
    }
}
