package umc.spring.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.BaseErrorCode;
import umc.spring.apiPayload.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4003", "선호 음식을 선택해주세요"),
    //Mission 관련 에러
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "해당 미션이 존재하지 않습니다"),
    MISSION_ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST, "MISSION4002", "해당 미션은 이미 진행중인 미션입니다."),
    //Store 관련 에러
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4001", "가게가 존재하지 않습니다."),
    //Region 관련 에러
    REGION_NOT_EXIST(HttpStatus.BAD_REQUEST, "REGION4001", "지역이 존재하지 않습니다."),
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "test exception");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason(){
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus(){
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
