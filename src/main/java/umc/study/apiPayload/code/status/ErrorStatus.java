package umc.study.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // 가게 관련
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "카테고리를 찾을 수 없습니다."),
    AREA_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4002", "지역을 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4003", "사용자를 찾을 수 없습니다."),
    STORE_ADDRESS_DUPLICATE(HttpStatus.CONFLICT, "STORE4004", "이미 동일한 주소에 가게가 등록되어 있습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4005", "존재하지 않는 가게입니다."),

    // 리뷰 관련
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4001", "존재하지 않는 미션입니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "REVIEW4002", "이미 해당 가게에 리뷰를 작성했습니다."),

    // 미션 관련
    MISSION_ALREADY_EXISTS(HttpStatus.CONFLICT, "MISSION4002", "이미 동일한 미션이 존재합니다."),
    MISSION_DATE_INVALID(HttpStatus.BAD_REQUEST, "MISSION4003", "유효하지 않은 미션 날짜입니다."),

    // 사용자 미션 관련
    MISSION_ALREADY_IN_PROGRESS(HttpStatus.CONFLICT, "USERMISSION4003", "이미 도전 중인 미션입니다."),
    MISSION_NOT_STARTED(HttpStatus.BAD_REQUEST, "USERMISSION4004", "아직 시작되지 않은 미션입니다."),
    MISSION_ENDED(HttpStatus.BAD_REQUEST, "USERMISSION4005", "이미 종료된 미션입니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}