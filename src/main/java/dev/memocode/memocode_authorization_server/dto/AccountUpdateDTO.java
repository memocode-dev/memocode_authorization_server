package dev.memocode.memocode_authorization_server.dto;

import dev.memocode.memocode_authorization_server.domain.account.entity.Authority;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountUpdateDTO {
    private Authority authority;
    private UUID accountId;
}
