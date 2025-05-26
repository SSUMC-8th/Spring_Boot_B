package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.AreaRepository;
import umc.study.validation.annotation.ExistArea;

@Component
@RequiredArgsConstructor
public class AreaExistValidator implements ConstraintValidator<ExistArea, Long> {

    private final AreaRepository areaRepository;

    @Override
    public void initialize(ExistArea constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true; // null은 @NotNull로 처리

        boolean isValid = areaRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            ErrorStatus.AREA_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}