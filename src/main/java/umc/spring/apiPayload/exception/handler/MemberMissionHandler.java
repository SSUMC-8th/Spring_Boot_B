package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class MemberMissionHandler extends GeneralException {

    public MemberMissionHandler(BaseErrorCode code) {
        super(code);
    }
}
