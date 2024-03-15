package dev.memocode.memocode_authorization_server.in.web.exception;

import dev.memocode.memocode_authorization_server.domain.exception.GlobalError;
import dev.memocode.memocode_authorization_server.domain.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public String Exception(Exception ex) {
        log.error("error : {}", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(GlobalException.class)
    public String globalException(GlobalException ex) {

        GlobalError error = ex.getError();

        log.error("error : {}", ex.getMessage());
        log.error("GlobalError : {}", error);

        return "error";
    }
}
