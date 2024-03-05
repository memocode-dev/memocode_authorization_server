package dev.memocode.memocode_authorization_server.api.spec;

import dev.memocode.memocode_authorization_server.dto.form.AccountUpdateForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "accounts", description = "계정 API")
@SecurityRequirement(name = "bearer-key")
public interface AccountApi {

    @Operation(summary = "계정 업데이트")
    ResponseEntity<Void> updateAccount(AccountUpdateForm form, UUID accountId);
}
