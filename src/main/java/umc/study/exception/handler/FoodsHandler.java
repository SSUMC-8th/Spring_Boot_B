package umc.study.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.exception.GeneralException;

public class FoodsHandler extends GeneralException {

    public FoodsHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
