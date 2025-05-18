package umc.study.repository.ShopRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Shop;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopRepositoryCustom {
    Optional<Shop> findById(Long id);
    Optional<Shop> findByName(String name);
}
