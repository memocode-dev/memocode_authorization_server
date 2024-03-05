package dev.memocode.memocode_authorization_server.dto;

import dev.memocode.memocode_authorization_server.domain.account.entity.AuthType;
import dev.memocode.memocode_authorization_server.domain.account.entity.Authority;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountCreateDTO {
    private String authId;
    private AuthType type;
    private String email;
    private Authority authority;
}
