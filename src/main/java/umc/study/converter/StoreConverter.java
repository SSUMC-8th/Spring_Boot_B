package umc.study.converter;

import umc.study.domain.Area;
import umc.study.domain.Store;
import umc.study.domain.StoreCategory;
import umc.study.domain.StoreMetrics;
import umc.study.domain.User;
import umc.study.dto.store.StoreRequestDTO;
import umc.study.dto.store.StoreResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.CreateStoreRequest request, User owner, StoreCategory category, Area area) {
        // Store 객체 생성
        Store store = Store.builder()
                .name(request.getName())
                .owner(owner)
                .category(category)
                .area(area)
                .streetAddress(request.getStreetAddress())
                .jibunAddress(request.getJibunAddress())
                .description(request.getDescription())
                .businessHours(request.getBusinessHours())
                .contactNumber(request.getContactNumber())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

        // StoreMetrics 생성 및 연결
        StoreMetrics storeMetrics = StoreMetrics.builder()
                .store(store)
                .rating(BigDecimal.ZERO)
                .ratingCount(0)
                .lastUpdated(LocalDateTime.now())
                .build();
        store.setStoreMetrics(storeMetrics);

        return store;
    }

    public static StoreResponseDTO.CreateStoreResponse toCreateStoreResponse(Store store) {
        Area area = store.getArea();
        String areaFullName = area.getProvince() + " " + area.getCity() + " " + area.getTown();

        return StoreResponseDTO.CreateStoreResponse.builder()
                .storeId(store.getId())
                .name(store.getName())
                .categoryName(store.getCategory().getName())
                .areaName(areaFullName)
                .streetAddress(store.getStreetAddress())
                .createdAt(store.getCreatedAt())
                .build();
    }
}