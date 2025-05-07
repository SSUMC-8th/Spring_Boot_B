package umc.study.repository.StoreRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QStore;
import umc.study.domain.QStoreMetrics;
import umc.study.domain.Store;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Long categoryId, Long areaId) {
        QStore store = QStore.store;
        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null && !name.isBlank()) {
            predicate.and(store.name.containsIgnoreCase(name));
        }

        if (categoryId != null) {
            predicate.and(store.category.id.eq(categoryId));
        }

        if (areaId != null) {
            predicate.and(store.area.id.eq(areaId));
        }

        return jpaQueryFactory
                .selectFrom(store)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Store> dynamicQueryWithNameAndRating(String name, BigDecimal minRating) {
        QStore store = QStore.store;
        QStoreMetrics metrics = QStoreMetrics.storeMetrics;

        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null && !name.isBlank()) {
            predicate.and(store.name.containsIgnoreCase(name));
        }

        if (minRating != null) {
            predicate.and(metrics.rating.goe(minRating));
        }

        return jpaQueryFactory
                .selectFrom(store)
                .join(store.storeMetrics, metrics)
                .where(predicate)
                .fetch();
    }

}
