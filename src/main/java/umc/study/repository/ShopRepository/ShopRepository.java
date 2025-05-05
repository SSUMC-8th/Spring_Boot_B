package umc.study.repository.ShopRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopRepositoryCustom {
}
