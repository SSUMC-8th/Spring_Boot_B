package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.domain.Area;
import umc.study.domain.Store;
import umc.study.domain.StoreCategory;
import umc.study.domain.User;
import umc.study.converter.StoreConverter;
import umc.study.dto.store.StoreRequestDTO;
import umc.study.repository.AreaRepository;
import umc.study.repository.StoreCategoryRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final StoreCategoryRepository storeCategoryRepository;
    private final AreaRepository areaRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Store createStore(StoreRequestDTO.CreateStoreRequest request, Long ownerId) {
        // 1. 유저, 카테고리, 지역 정보 조회
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.USER_NOT_FOUND));

        StoreCategory category = storeCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.CATEGORY_NOT_FOUND));

        Area area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.AREA_NOT_FOUND));

        // 2. 중복 주소 체크
        if (storeRepository.existsByStreetAddressAndJibunAddress(
                request.getStreetAddress(), request.getJibunAddress())) {
            throw new StoreHandler(ErrorStatus.STORE_ADDRESS_DUPLICATE);
        }

        // 3. Store 생성
        Store store = StoreConverter.toStore(request, owner, category, area);

        // 4. 저장
        return storeRepository.save(store);
    }
}