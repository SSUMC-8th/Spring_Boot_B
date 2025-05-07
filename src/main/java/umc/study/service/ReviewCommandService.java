package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.repository.ReviewRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {
    private final ReviewRepository repo;

    @Transactional
    public Review createReview(Long userId, Long storeId, BigDecimal rating, String content) {
        Review rev = Review.builder()
                .user(User.builder().id(userId).build())
                .store(Store.builder().id(storeId).build())
                .rating(rating)
                .content(content)
                .build();
        return repo.save(rev);
    }
}
