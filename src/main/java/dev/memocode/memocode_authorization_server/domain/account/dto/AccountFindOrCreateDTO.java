package dev.memocode.memocode_authorization_server.domain.account.dto;

import dev.memocode.memocode_authorization_server.domain.account.entity.AuthType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountFindOrCreateDTO {

    @NotNull(message = "AUTH_ID_NOT_NULL:auth_id must not be null")
    private String authId;

    @NotNull(message = "AUTH_TYPE_ID_NOT_NULL:auth_type must not be null")
    private AuthType type;

    @NotNull(message = "EMAIL_NOT_NULL:email must not be null")
    private String email;
}
