package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.AlreadyChallengedValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlreadyChallengedValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NotAlreadyChallenged {
    String message() default "이미 진행중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {}; //메타데이터를 전달할 때 사용
}
