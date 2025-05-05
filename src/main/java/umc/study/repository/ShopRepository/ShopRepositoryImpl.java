package umc.study.repository.ShopRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QShop;
import umc.study.domain.Shop;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopRepositoryImpl implements ShopRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QShop shop = QShop.shop;

    @Override
    public List<Shop> dynamicQueryWithBooleanBuilder(String name, Float score) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null) {
            predicate.and(shop.name.eq(name));
        }

        if (score != null) {
            predicate.and(shop.score.goe(4.0f));
        }

        return jpaQueryFactory
                .selectFrom(shop)
                .where(predicate)
                .fetch();
    }
}
