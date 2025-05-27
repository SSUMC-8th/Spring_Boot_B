package umc.study.service.MissionService;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;

public interface MissionQueryService {

    // 특정 가게의 활성화된 미션 목록 조회
    Page<Mission> getStoreMissions(Long storeId, Integer page);
}