package umc.study.service.ShopService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.RegionShopConverter;
import umc.study.domain.Region;
import umc.study.domain.Shop;
import umc.study.exception.handler.GeneralHandler;
import umc.study.repository.RegionRepository;
import umc.study.repository.ShopRepository.ShopRepository;
import umc.study.web.dto.RegionShopRequestDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {

    private final ShopRepository shopRepository;
    private final RegionRepository regionRepository;

    public Shop joinShop(RegionShopRequestDTO.SaveShopDTO request) {

        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.REGION_NOT_FOUND));

        Shop newShop = RegionShopConverter.toShop(request, region);

        return shopRepository.save(newShop);
    }

    public boolean existShop(Long shopId) {
        Optional<Shop> shop = shopRepository.findById(shopId);

        return shop.isPresent();
    }

    public Shop findById(Long shopId) {
        return shopRepository.findById(shopId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.SHOP_NOT_FOUND));
    }

}
