package umc.study.service.ShopService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopQueryService {

    Optional<Shop> findShop(Long id);
    List<Shop> findShopsByNameAndScore(String name, Float score);
    Page<Review> getReviewList(Long shopId, Integer page);
}
