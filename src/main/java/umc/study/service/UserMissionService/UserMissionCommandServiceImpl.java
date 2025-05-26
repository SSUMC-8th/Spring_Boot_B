package umc.study.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.UserMissionHandler;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.domain.UserMission;
import umc.study.dto.userMission.UserMissionRequestDTO;
import umc.study.repository.MissionRepository;
import umc.study.repository.UserMissionRepository;
import umc.study.repository.UserRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionCommandServiceImpl implements UserMissionCommandService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserMission joinMission(UserMissionRequestDTO.JoinMissionRequest request, Long userId) {
        // 1. 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserMissionHandler(ErrorStatus.USER_NOT_FOUND));

        // 2. 미션 조회
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new UserMissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // 3. 미션 기간 유효성 검사
        validateMissionDate(mission);

        // 4. 이미 도전 중인 미션인지 확인 (이미 validator에서 검증했지만 안전을 위해 한번 더 체크)
        if (userMissionRepository.existsByUserAndMission(user, mission)) {
            throw new UserMissionHandler(ErrorStatus.MISSION_ALREADY_IN_PROGRESS);
        }

        // 5. UserMission 생성
        UserMission userMission = UserMissionConverter.toUserMission(user, mission);

        // 6. 저장
        return userMissionRepository.save(userMission);
    }

    // 미션 기간 유효성 검사
    private void validateMissionDate(Mission mission) {
        LocalDate currentDate = LocalDate.now();

        // 미션 시작일이 현재 날짜 이후인지 확인
        if (mission.getStartDate().isAfter(currentDate)) {
            throw new UserMissionHandler(ErrorStatus.MISSION_NOT_STARTED);
        }

        // 미션 종료일이 현재 날짜 이전인지 확인
        if (mission.getEndDate().isBefore(currentDate)) {
            throw new UserMissionHandler(ErrorStatus.MISSION_ENDED);
        }
    }
}