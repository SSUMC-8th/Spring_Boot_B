package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class StoreResponseDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddStoreToRegionResultDto{
        Long storeId;
        LocalDateTime createdAt;
    }
}
