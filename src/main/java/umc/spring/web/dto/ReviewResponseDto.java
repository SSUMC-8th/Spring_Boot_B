package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewAddResultDto{
        Long reviewId;
        LocalDateTime createdAt;
    }
}
