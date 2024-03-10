package dev.memocode.memocode_authorization_server.dto;

import dev.memocode.memocode_authorization_server.domain.account.entity.Authority;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountUpdateDTO {

    @NotNull(message = "AUTHORITY_NOT_NULL:accountId must not be null")
    private Authority authority;

    @NotNull(message = "ACCOUNT_ID_NOT_NULL:accountId must not be null")
    private UUID accountId;
}
