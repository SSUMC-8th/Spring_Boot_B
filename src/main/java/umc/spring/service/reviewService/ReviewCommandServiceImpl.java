package umc.spring.service.reviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.apiPayload.exception.handler.TempHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review addReview(ReviewRequestDto.ReviewAddDto request){
        Review review = ReviewConverter.toReview(request);
        List<String> urls = request.getImageUrl();
        if (urls != null && !urls.isEmpty()) {
            for (String url : urls) {
                review.addReviewImage(
                        ReviewImage.builder()
                                .imageUrl(url)
                                .build()
                );
            }
        }

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(()->new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(1L) //하드코딩
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        review.setStore(store);
        review.setMember(member);
        return reviewRepository.save(review);

    }
}
