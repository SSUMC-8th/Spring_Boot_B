package umc.study.service.StoreService;

import umc.study.domain.Store;
import umc.study.dto.store.StoreRequestDTO;

public interface StoreCommandService {
    Store createStore(StoreRequestDTO.CreateStoreRequest request, Long ownerId);
}