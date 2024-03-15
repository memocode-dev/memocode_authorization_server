package dev.memocode.memocode_authorization_server.in.api.exception;

import dev.memocode.memocode_authorization_server.domain.exception.GlobalError;
import dev.memocode.memocode_authorization_server.domain.exception.GlobalErrorCode;
import dev.memocode.memocode_authorization_server.domain.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "me.farmfarm.authorization_server.api")
@RequiredArgsConstructor
public class ApiGlobalExceptionController {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> globalException(GlobalException ex) {

        GlobalError error = ex.getError();

        ErrorResponse response = ErrorResponse.builder()
                .code(error.getCode().getCode())
                .codeString(error.getCode().name())
                .message(error.getMessage())
                .build();

        return ResponseEntity.status(error.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex) {

        ErrorResponse response = ErrorResponse.builder()
                .code(GlobalErrorCode.INTERNAL_ERROR.getCode())
                .codeString(GlobalErrorCode.INTERNAL_ERROR.name())
                .message(GlobalErrorCode.INTERNAL_ERROR.getMessage())
                .build();

        return ResponseEntity.status(GlobalErrorCode.INTERNAL_ERROR.getCode()).body(response);
    }
}
