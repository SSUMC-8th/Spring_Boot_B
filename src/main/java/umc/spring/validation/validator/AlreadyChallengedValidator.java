package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.ErrorStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.service.memberMissionService.MemberMissionCommandService;
import umc.spring.validation.annotation.NotAlreadyChallenged;
import umc.spring.web.dto.MissionRequestDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlreadyChallengedValidator implements ConstraintValidator<NotAlreadyChallenged, MissionRequestDto.ChallengeMissionDto> {

    private final MemberMissionCommandService memberMissionCommandService;

    @Override
    public void initialize(NotAlreadyChallenged constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionRequestDto.ChallengeMissionDto request, ConstraintValidatorContext context) {

        boolean isValid = !(memberMissionCommandService.existsByMemberIdAndMissionId(request.getMemberId(), request.getMissionId()));
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString()).addConstraintViolation();
        }
        return isValid;
    }
}
