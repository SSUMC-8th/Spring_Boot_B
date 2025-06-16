package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReviewPreviewListDto{
        List<ReviewPreviewDto> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReviewPreviewDto{
        String username;
        String content;
        Float score;
        LocalDate createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class MissionPreviewListDto{
        List<MemberResponseDto.MissionPreviewDto> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class MissionPreviewDto{
        String description;
        MissionStatus status;
        Integer point;
        LocalDate deadline;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class LoginResultDto{
        Long memberId;
        String accessToken;
    }
    @Builder
    @Getter
    @AllArgsConstructor
    public static class MemberInfoDto{
        String name;
        String email;
        Gender gender;
    }

}
