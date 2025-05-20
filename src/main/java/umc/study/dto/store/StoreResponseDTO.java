package umc.study.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStoreResponse {
        private Long storeId;
        private String name;
        private String categoryName;
        private String areaName;
        private String streetAddress;
        private LocalDateTime createdAt;
    }
}