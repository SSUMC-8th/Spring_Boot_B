package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Store;
import umc.study.dto.store.StoreRequestDTO;
import umc.study.dto.store.StoreResponseDTO;
import umc.study.service.StoreService.StoreCommandService;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StoreResponseDTO.CreateStoreResponse> createStore(
            @RequestBody @Valid StoreRequestDTO.CreateStoreRequest request,
            @RequestParam(required = false) Long ownerId) {

        // 실제 환경에서는 인증된 사용자 정보를 가져와야 하지만
        // 과제 조건에 따라 하드코딩된 사용자 ID 사용합니다잇~
        if (ownerId == null) {
            ownerId = 1L; // 하드코딩된 사용자 ID
        }

        Store createdStore = storeCommandService.createStore(request, ownerId);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreResponse(createdStore));
    }
}