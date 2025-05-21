package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.service.ShopService.ShopService;
import umc.study.validation.annotation.ExistShop;

@Component
@RequiredArgsConstructor
public class ShopExistValidator implements ConstraintValidator<ExistShop, Long> {

    private final ShopService shopService;

    @Override
    public void initialize(ExistShop constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long shopId, ConstraintValidatorContext context) {
        if (shopId == null) {
            return true;
        }

        boolean isValid = shopService.existShop(shopId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.SHOP_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
