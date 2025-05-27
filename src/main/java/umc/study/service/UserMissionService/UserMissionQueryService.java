package umc.study.service.UserMissionService;

import org.springframework.data.domain.Page;
import umc.study.domain.UserMission;
import umc.study.dto.UserMissionDto;

import java.util.List;

public interface UserMissionQueryService {

    // 내가 진행중인 미션 목록 조회
    Page<UserMission> getMyInProgressMissions(Long userId, Integer page);

    // QueryDSL을 활용한 효율적인 조회
    List<UserMissionDto> getMyInProgressMissionDtos(Long userId, Long cursorId, Integer pageSize);
}