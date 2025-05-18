package umc.spring.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDto {
    @Getter
    public static class ReviewAddDto{
        @NotBlank
        String content;
        @Max(5) @Min(0)
        Float score;
        @NotNull
        Long storeId;

        List<String> imageUrl;
    }
}
