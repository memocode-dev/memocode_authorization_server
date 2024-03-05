package dev.memocode.memocode_authorization_server.api.spec;

import dev.memocode.memocode_authorization_server.dto.form.TokenCreateForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Tag(name = "token", description = "토큰 API")
public interface TokenApi {

    @Operation(summary = "code를 통한 액세스토큰 및 리프레시 토큰 발급, 리프레시 토큰을 통한 액세스토큰 발급")
    ResponseEntity<Map> getToken(TokenCreateForm form, String refresh_token);

    @Operation(summary = "리프레시 토큰 삭제")
    ResponseEntity<Map> removeRefreshToken ();
}
