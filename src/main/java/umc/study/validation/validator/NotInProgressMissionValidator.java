package umc.study.validation.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.domain.Mission;
import umc.study.domain.User;
import umc.study.repository.MissionRepository;
import umc.study.repository.UserMissionRepository;
import umc.study.repository.UserRepository;
import umc.study.validation.annotation.NotInProgressMission;

@Component
@RequiredArgsConstructor
public class NotInProgressMissionValidator implements ConstraintValidator<NotInProgressMission, Long> {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Override
    public void initialize(NotInProgressMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        if (missionId == null) return true; // null은 @NotNull로 처리

        // 하드코딩된 사용자 ID 사용 - 실제 환경에서는 인증에서 사용자 ID를 가져와야 함
        Long userId = 1L;

        // 미션 존재 여부 확인 (이미 @ExistMission으로 검증하지만 안전을 위해 한번 더 체크)
        Mission mission = missionRepository.findById(missionId).orElse(null);
        if (mission == null) return true; // 존재하지 않는 미션은 @ExistMission에서 처리

        // 사용자 조회
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return true; // 존재하지 않는 사용자는 서비스 단에서 처리

        // 이미 도전 중인 미션인지 확인
        boolean isAlreadyInProgress = userMissionRepository.existsByUserAndMission(user, mission);

        if (isAlreadyInProgress) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            ErrorStatus.MISSION_ALREADY_IN_PROGRESS.toString())
                    .addConstraintViolation();
        }

        return !isAlreadyInProgress;
    }
}