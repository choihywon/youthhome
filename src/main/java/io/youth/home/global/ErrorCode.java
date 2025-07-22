package io.youth.home.global;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //jwt
    NO_REFRESH_TOKEN_COOKIE(HttpStatus.UNAUTHORIZED, "리프레시 토큰을 발급하지 않았습니다."),
    MISSING_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 존재하지 않습니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 만료되었습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 리프레시 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "저장된 리프레시 토큰이 없습니다."),



    //예시
    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    USER_EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    USER_ALREADY_DEACTIVATED(HttpStatus.BAD_REQUEST, "이미 탈퇴된 사용자입니다."),
    USER_ALREADY_RESTORED(HttpStatus.BAD_REQUEST, "이미 복구된 사용자입니다."),
    USER_RESTORE_EXPIRED(HttpStatus.BAD_REQUEST, "복구 가능 기간이 만료되었습니다."),
    USER_UNAUTHORIZED(HttpStatus.FORBIDDEN, "본인만 할 수 있습니다.");



    private final HttpStatus status;
    private final String message;
}
