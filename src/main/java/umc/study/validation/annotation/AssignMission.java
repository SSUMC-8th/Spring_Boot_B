package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.MissionAssignValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionAssignValidator.class)
@Target( {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AssignMission {

    String message() default "이미 할당된 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
