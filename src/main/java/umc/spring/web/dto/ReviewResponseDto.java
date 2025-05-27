package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewAddResultDto{
        Long reviewId;
        LocalDateTime createdAt;
    }





}
