package umc.study.service.ShopService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.RegionShopConverter;
import umc.study.domain.Shop;
import umc.study.exception.handler.GeneralHandler;
import umc.study.repository.ShopRepository.ShopRepository;
import umc.study.web.dto.RegionShopRequestDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {

    private final ShopRepository shopRepository;
    private final RegionShopConverter regionShopConverter;

    public Shop joinShop(RegionShopRequestDTO.SaveShopDTO request) {
        Shop newShop = regionShopConverter.toShop(request);

        return shopRepository.save(newShop);
    }

    public boolean existShop(String shopName) {
        Optional<Shop> findShop = shopRepository.findByName(shopName);

        return findShop.isPresent();
    }

    public Shop findById(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new GeneralHandler(ErrorStatus.SHOP_NOT_FOUND));
    }

}
