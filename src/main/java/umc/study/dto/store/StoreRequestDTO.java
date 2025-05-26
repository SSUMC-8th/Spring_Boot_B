package umc.study.dto.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.validation.annotation.ExistArea;
import umc.study.validation.annotation.ExistCategory;

import java.math.BigDecimal;

public class StoreRequestDTO {

    @Getter
    public static class CreateStoreRequest {
        @NotBlank(message = "가게 이름은 필수입니다")
        private String name;

        @NotNull(message = "카테고리 ID는 필수입니다")
        @ExistCategory
        private Long categoryId;

        @NotNull(message = "지역 ID는 필수입니다")
        @ExistArea
        private Long areaId;

        @NotBlank(message = "도로명 주소는 필수입니다")
        private String streetAddress;

        private String jibunAddress;

        private String description;

        private String businessHours;

        private String contactNumber;

        private BigDecimal latitude;

        private BigDecimal longitude;
    }
}