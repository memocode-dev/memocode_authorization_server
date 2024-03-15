package dev.memocode.memocode_authorization_server.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum GlobalErrorCode {

    INTERNAL_ERROR(INTERNAL_SERVER_ERROR, 500, "서버 에러, 관리자에게 문의하세요", GlobalErrorCodeType.CRITICAL),
    UNEXPECTED_API_RESPONSE(BAD_GATEWAY, 502, "예상치 못한 API 응답입니다.", GlobalErrorCodeType.CRITICAL),
    UNSUPPORTED_SOCIAL_PLATFORM(INTERNAL_SERVER_ERROR, 500, "지원하지 않는 소셜 플랫폼", GlobalErrorCodeType.ERROR),

    ACCOUNT_AUTHORITY_NOT_FOUND(INTERNAL_SERVER_ERROR, 500, "해당 계정 권한을 찾을 수 없습니다.", GlobalErrorCodeType.CRITICAL),

    AUTHORITY_ALREAY_EXISTS(BAD_GATEWAY, 10000, "이미 존재하는 권한입니다.", GlobalErrorCodeType.INFO),

    AUTHORITY_NOT_FOUND(INTERNAL_SERVER_ERROR, 500, "해당 권한을 찾을 수 없습니다.", GlobalErrorCodeType.CRITICAL),

    ACCOUNT_NOT_FOUND(NOT_FOUND, 404, "해당 계정을 찾을 수 없습니다.", GlobalErrorCodeType.CRITICAL),

    ;

    private final HttpStatus status;
    private final int code;
    private final String message;
    private final GlobalErrorCodeType type;

    GlobalErrorCode(HttpStatus status, int code, String message, GlobalErrorCodeType type) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.type = type;
    }
}
