package umc.study.service.ShopService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Shop;
import umc.study.repository.ShopRepository.ShopRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopQueryServiceImpl implements ShopQueryService{

    private final ShopRepository shopRepository;

    @Override
    public Optional<Shop> findShop(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public List<Shop> findShopsByNameAndScore(String name, Float score) {
        List<Shop> filteredShops = shopRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredShops.forEach(shop -> System.out.println("Shop: " + shop));

        return filteredShops;
    }
}
