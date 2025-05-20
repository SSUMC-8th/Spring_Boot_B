package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.StoreCategory;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {
}