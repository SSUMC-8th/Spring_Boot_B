package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserAndStore(User user, Store store);
    Optional<Review> findByUserAndStore(User user, Store store);

    // 특정 사용자가 작성한 리뷰 목록 조회 (페이징)
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.store s " +
            "LEFT JOIN FETCH r.mission m " +
            "WHERE r.user = :user " +
            "ORDER BY r.createdAt DESC")
    Page<Review> findAllByUserOrderByCreatedAtDesc(@Param("user") User user, PageRequest pageRequest);

    // 특정 가게의 리뷰 목록 조회 (페이징) - 워크북 예시용
    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.user u " +
            "WHERE r.store = :store " +
            "ORDER BY r.createdAt DESC")
    Page<Review> findAllByStoreOrderByCreatedAtDesc(@Param("store") Store store, PageRequest pageRequest);

}