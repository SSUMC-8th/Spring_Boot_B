package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class RegionShopRequestDTO {

    @Getter
    public static class SaveShopDTO{
        @NotNull
        Long regionId;
        @NotBlank
        String name;
        @Size(min = 2, max = 30)
        String address;
        @NotNull
        Float score;
        String category;
        @NotBlank
        String shopInfo;
    }
}
