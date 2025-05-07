package umc.study.repository.StoreRepository;

import umc.study.domain.Store;

import java.math.BigDecimal;
import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Long categoryId, Long areaId);
    List<Store> dynamicQueryWithNameAndRating(String name, BigDecimal minRating);

}
