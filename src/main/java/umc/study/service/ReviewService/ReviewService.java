package umc.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Shop;
import umc.study.exception.handler.GeneralHandler;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.repository.ShopRepository.ShopRepository;
import umc.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ShopRepository shopRepository;
    private final ReviewRepository reviewRepository;

    public Review joinReview(ReviewRequestDTO.WriteReviewDTO request) {

        Member member = memberRepository.findById(request.getReviewerId())
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Shop shop = shopRepository.findById(request.getReviewShopId())
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.SHOP_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member, shop);

        return reviewRepository.save(review);
    }
}
