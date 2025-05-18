package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDto {
    @Getter
    public static class AddStoreToRegionDto{
        @NotNull
        String name;
        @NotBlank
        String address;
        @NotNull
        Long regionId;
    }
}
