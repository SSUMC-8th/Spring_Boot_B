package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.ReviewHandler;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.converter.ReviewConverter;
import umc.study.dto.review.ReviewRequestDTO;
import umc.study.repository.MissionRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.UserRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Review createReview(ReviewRequestDTO.CreateReviewRequest request, Long userId) {
        // 1. 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.USER_NOT_FOUND));

        // 2. 가게 조회
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.STORE_NOT_FOUND));

        // 3. 중복 리뷰 검사
        if (reviewRepository.existsByUserAndStore(user, store)) {
            throw new ReviewHandler(ErrorStatus.REVIEW_ALREADY_EXISTS);
        }

        // 4. 미션 조회 (선택적)
        Mission mission = null;
        if (request.getMissionId() != null) {
            mission = missionRepository.findById(request.getMissionId())
                    .orElseThrow(() -> new ReviewHandler(ErrorStatus.MISSION_NOT_FOUND));

            // 미션이 해당 가게의 미션인지 확인
            if (!mission.getStore().getId().equals(store.getId())) {
                throw new ReviewHandler(ErrorStatus.MISSION_NOT_FOUND);
            }
        }

        // 5. Review 생성
        Review review = ReviewConverter.toReview(request, user, store, mission);

        // 6. 가게 평점 업데이트
        updateStoreRating(store, request.getRating());

        // 7. 저장
        return reviewRepository.save(review);
    }

    // 가게 평점 업데이트 메서드
    private void updateStoreRating(Store store, BigDecimal newRating) {
        // 현재 가게의 평점 정보 가져오기
        BigDecimal currentRating = store.getStoreMetrics().getRating();
        Integer ratingCount = store.getStoreMetrics().getRatingCount();

        // 새로운 평점 계산
        BigDecimal totalRating = currentRating.multiply(new BigDecimal(ratingCount)).add(newRating);
        int newCount = ratingCount + 1;
        BigDecimal updatedRating = totalRating.divide(new BigDecimal(newCount), 2, RoundingMode.HALF_UP);

        // 가게 평점 정보 업데이트
        store.getStoreMetrics().setRating(updatedRating);
        store.getStoreMetrics().setRatingCount(newCount);
        store.getStoreMetrics().setLastUpdated(java.time.LocalDateTime.now());
    }
}