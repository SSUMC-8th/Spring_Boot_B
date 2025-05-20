package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode code){super(code);}
}
