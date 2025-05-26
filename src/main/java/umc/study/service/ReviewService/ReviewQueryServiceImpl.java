package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.ReviewHandler;
import umc.study.domain.Review;
import umc.study.domain.User;
import umc.study.repository.ReviewRepository;
import umc.study.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Review> getMyReviews(Long userId, Integer page) {
        // 1. 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.USER_NOT_FOUND));

        // 2. 페이징 처리하여 리뷰 목록 조회 (한 페이지에 10개씩)
        PageRequest pageRequest = PageRequest.of(page, 10);

        return reviewRepository.findAllByUserOrderByCreatedAtDesc(user, pageRequest);
    }
}