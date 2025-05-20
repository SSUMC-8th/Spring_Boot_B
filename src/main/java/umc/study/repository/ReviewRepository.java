package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserAndStore(User user, Store store);
    Optional<Review> findByUserAndStore(User user, Store store);
}