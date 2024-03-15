package dev.memocode.memocode_authorization_server.domain.account.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountUpdateAfterSignupDTO {
    @NotNull(message = "USER_ID_NOT_NULL:userId must not be null")
    private UUID userId;
}
