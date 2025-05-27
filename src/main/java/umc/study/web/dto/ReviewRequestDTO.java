package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistShop;

public class ReviewRequestDTO {

    @Getter
    public static class WriteReviewDTO {
        @NotNull
        Long reviewerId;
        @ExistShop
        Long reviewShopId;
        String reviewText;
        @NotNull
        Float star;
    }

}
