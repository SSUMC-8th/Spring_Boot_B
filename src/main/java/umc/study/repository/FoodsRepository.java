package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Foods;

public interface FoodsRepository extends JpaRepository<Foods, Long> {
}
