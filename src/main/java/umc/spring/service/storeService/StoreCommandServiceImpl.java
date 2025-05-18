package umc.spring.service.storeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.web.dto.StoreRequestDto;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store addStore(StoreRequestDto.AddStoreToRegionDto request){
        Store store = StoreConverter.toStore(request);
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(()->new RegionHandler(ErrorStatus.REGION_NOT_EXIST));
        region.addStore(store);
        return storeRepository.save(store);
    }

}
