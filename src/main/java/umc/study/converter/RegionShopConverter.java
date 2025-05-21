package umc.study.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.Region;
import umc.study.domain.Shop;
import umc.study.web.dto.RegionShopRequestDTO;
import umc.study.web.dto.RegionShopResponseDTO;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class RegionShopConverter {

    public static RegionShopResponseDTO.SaveShopResultDTO toSaveShopResultDTO(Shop shop) {
        return RegionShopResponseDTO.SaveShopResultDTO.builder()
                .shopId(shop.getId())
                .shopName(shop.getName())
                .regionName(shop.getRegion().getName())
                .build();
    }

    public static Shop toShop(RegionShopRequestDTO.SaveShopDTO request, Region region) {
        return Shop.builder()
                .region(region)
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .category(request.getCategory())
                .shopInfo(request.getShopInfo())
                .reviewList(new ArrayList<>())
                .missionList(new ArrayList<>())
                .build();
    }
}
