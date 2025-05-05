package umc.study.repository.ShopRepository;

import umc.study.domain.Shop;

import java.util.List;

public interface ShopRepositoryCustom {
    List<Shop> dynamicQueryWithBooleanBuilder(String name, Float score);
}
