package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {
}