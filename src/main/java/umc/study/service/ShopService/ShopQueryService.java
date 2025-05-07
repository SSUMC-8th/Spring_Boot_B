package umc.study.service.ShopService;

import umc.study.domain.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopQueryService {

    Optional<Shop> findShop(Long id);
    List<Shop> findShopsByNameAndScore(String name, Float score);
}
