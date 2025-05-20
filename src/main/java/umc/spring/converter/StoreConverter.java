package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDto;
import umc.spring.web.dto.StoreResponseDto;

import java.time.LocalDateTime;

public class StoreConverter {
    public static StoreResponseDto.AddStoreToRegionResultDto toAddStoreToRegionResultDto(Store store){
        return StoreResponseDto.AddStoreToRegionResultDto.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDto.AddStoreToRegionDto request){
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }
}
