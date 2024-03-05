package dev.memocode.memocode_authorization_server.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class GlobalError {

    private GlobalErrorCode code;

    private String message;

    private HttpStatus status;

    private String logMessage;

    public static GlobalError of(GlobalErrorCode code) {
        return GlobalError.builder()
                .code(code)
                .status(code.getStatus())
                .message(code.getMessage())
                .logMessage(code.getMessage())
                .build();
    }

    public static GlobalError of(GlobalErrorCode code, String logMessage) {
        return GlobalError.builder()
                .code(code)
                .status(code.getStatus())
                .message(code.getMessage())
                .logMessage(logMessage)
                .build();
    }
}
