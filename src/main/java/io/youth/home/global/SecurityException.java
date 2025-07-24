package io.youth.home.global;



import io.youth.home.global.ErrorCode;
import lombok.Getter;

@Getter
public class SecurityException extends RuntimeException {

    private final ErrorCode errorCode;

    public SecurityException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // 이게 핵심!
        this.errorCode = errorCode;
    }

    public SecurityException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
    }
}
