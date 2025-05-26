package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;
import umc.study.domain.Store;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    boolean existsByStoreAndTitleAndStartDateAndEndDate(Store store, String title, LocalDate startDate, LocalDate endDate);
    List<Mission> findByStore(Store store);
}