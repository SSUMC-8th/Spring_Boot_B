package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistShop;

public class ReviewRequestDTO {

    @Getter
    public static class WriteReviewDTO {
        @NotBlank
        String reviewer;
        @ExistShop
        String reviewShop;
        String reviewText;
        @NotNull
        Float star;
    }
}
