package umc.spring.service.storeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.storeRepository.StoreRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId).get();
        log.info("store:{}",store.getId());
        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page,10));
        for (Review review : storePage) {
            log.info("review:{}", review);
        }
        return storePage;
    }
}
