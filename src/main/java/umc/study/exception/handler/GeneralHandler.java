package umc.study.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.exception.GeneralException;

public class GeneralHandler extends GeneralException {

    public GeneralHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
