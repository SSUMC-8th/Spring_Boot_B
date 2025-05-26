package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    boolean existsByStoreAndTitleAndStartDateAndEndDate(Store store, String title, LocalDate startDate, LocalDate endDate);
    List<Mission> findByStore(Store store);

    // 특정 가게의 미션 목록 조회 (페이징, ACTIVE 상태만)
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH m.area a " +
            "WHERE m.store = :store AND m.status = :status " +
            "ORDER BY m.createdAt DESC")
    Page<Mission> findAllByStoreAndStatusOrderByCreatedAtDesc(
            @Param("store") Store store,
            @Param("status") MissionStatus status,
            PageRequest pageRequest);

    // 특정 가게의 모든 미션 목록 조회 (상태 무관)
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH m.area a " +
            "WHERE m.store = :store " +
            "ORDER BY m.createdAt DESC")
    Page<Mission> findAllByStoreOrderByCreatedAtDesc(
            @Param("store") Store store,
            PageRequest pageRequest);
}