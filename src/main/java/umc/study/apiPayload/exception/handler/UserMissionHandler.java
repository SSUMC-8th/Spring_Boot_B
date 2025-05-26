package umc.study.apiPayload.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.exception.GeneralException;

public class UserMissionHandler extends GeneralException {
    public UserMissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}