package umc.study.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
