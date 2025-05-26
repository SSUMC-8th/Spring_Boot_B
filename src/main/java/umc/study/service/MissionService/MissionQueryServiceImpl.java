package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Page<Mission> getStoreMissions(Long storeId, Integer page) {
        // 1. 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.STORE_NOT_FOUND));

        // 2. 페이징 처리하여 해당 가게의 활성화된 미션 목록 조회 (한 페이지에 10개씩)
        PageRequest pageRequest = PageRequest.of(page, 10);

        return missionRepository.findAllByStoreAndStatusOrderByCreatedAtDesc(
                store, MissionStatus.ACTIVE, pageRequest);
    }
}