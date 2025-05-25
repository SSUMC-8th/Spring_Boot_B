package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.AlreadyChallengedValidator;
import umc.spring.validation.validator.ValidPageValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPageValidator.class)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ValidPage {
    String message() default "유효하지 않은 페이지입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
