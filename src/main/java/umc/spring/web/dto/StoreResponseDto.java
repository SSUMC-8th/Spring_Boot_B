package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StoreResponseDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddStoreToRegionResultDto{
        Long storeId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ReviewPreviewListDto{
        List<StoreResponseDto.ReviewPreviewDto> reviewList;
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
        String ownerNickname;
        Float score;
        String content;
        LocalDate createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class MissionPreviewListDto{
        List<StoreResponseDto.MissionPreviewDto> missionList;
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
        Integer point;
        LocalDate deadline;
    }
}
