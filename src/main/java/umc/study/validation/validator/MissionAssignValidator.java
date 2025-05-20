package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.repository.MissionAssignmentRepository;
import umc.study.service.MemberSerivce.MemberService;
import umc.study.service.MissionService.MissionService;
import umc.study.validation.annotation.AssignMission;
import umc.study.web.dto.MissionChallengeRequestDTO;

@Component
@RequiredArgsConstructor
public class MissionAssignValidator implements ConstraintValidator<AssignMission, MissionChallengeRequestDTO.AssignMissionDTO> {

    private final MissionAssignmentRepository missionAssignmentRepository;
    private final MemberService memberService;
    private final MissionService missionService;

    @Override
    public void initialize(AssignMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionChallengeRequestDTO.AssignMissionDTO request, ConstraintValidatorContext context) {

        Member member = memberService.findByName(request.getMissionMember());
        Mission mission = missionService.findByMissionId(request.getMissionId());

        boolean isValid = missionAssignmentRepository.existsMissionAssignmentByMemberAndMission(member, mission);

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
