package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.MissionAssignmentRepository;
import umc.study.service.MemberSerivce.MemberService;
import umc.study.service.MissionService.MissionService;
import umc.study.validation.annotation.AssignMission;
import umc.study.web.dto.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class MissionAssignValidator implements ConstraintValidator<AssignMission, MissionRequestDTO.AssignMissionDTO> {

    private final MissionAssignmentRepository missionAssignmentRepository;
    private final MemberService memberService;
    private final MissionService missionService;

    @Override
    public void initialize(AssignMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionRequestDTO.AssignMissionDTO request, ConstraintValidatorContext context) {

        boolean isValid = missionAssignmentRepository.existsMissionAssignmentByMemberIdAndMissionId(request.getMissionMemberId(), request.getMissionId());

        if (isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_ASSIGNED.toString())
                    .addPropertyNode("missionId")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
