package umc.study.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.UserMissionHandler;
import umc.study.domain.User;
import umc.study.domain.UserMission;
import umc.study.domain.enums.UserMissionStatus;
import umc.study.dto.UserMissionDto;
import umc.study.repository.UserMissionQueryRepository;
import umc.study.repository.UserMissionRepository;
import umc.study.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryServiceImpl implements UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;
    private final UserMissionQueryRepository userMissionQueryRepository;
    private final UserRepository userRepository;

    @Override
    public Page<UserMission> getMyInProgressMissions(Long userId, Integer page) {
        // 1. 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserMissionHandler(ErrorStatus.USER_NOT_FOUND));

        // 2. 진행중인 상태들 정의
        List<UserMissionStatus> inProgressStatuses = Arrays.asList(
                UserMissionStatus.IN_PROGRESS,
                UserMissionStatus.COMPLETED_PENDING,
                UserMissionStatus.COMPLETED
        );

        // 3. 페이징 처리하여 진행중인 미션 목록 조회 (한 페이지에 10개씩)
        PageRequest pageRequest = PageRequest.of(page, 10);

        return userMissionRepository.findAllByUserAndStatusInOrderByCreatedAtDesc(
                user, inProgressStatuses, pageRequest);
    }

    @Override
    public List<UserMissionDto> getMyInProgressMissionDtos(Long userId, Long cursorId, Integer pageSize) {
        // 사용자 검증
        userRepository.findById(userId)
                .orElseThrow(() -> new UserMissionHandler(ErrorStatus.USER_NOT_FOUND));

        return userMissionQueryRepository.findUserMissions(userId, cursorId, pageSize != null ? pageSize : 10);
    }
}