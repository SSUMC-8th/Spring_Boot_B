package umc.spring.service.storeService;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDto;

public interface StoreCommandService {
    public Store addStore(StoreRequestDto.AddStoreToRegionDto request);

}
