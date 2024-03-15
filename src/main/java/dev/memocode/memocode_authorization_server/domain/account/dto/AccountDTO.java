package dev.memocode.memocode_authorization_server.domain.account.dto;

import dev.memocode.memocode_authorization_server.domain.account.entity.AuthType;
import dev.memocode.memocode_authorization_server.domain.account.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private UUID id;
    private UUID userId;
    private String email;
    private String authId;
    private AuthType authType;
    private Authority authority;
    private Instant deletedAt;
    private Boolean deleted;
    private Instant createdAt;
    private Instant updatedAt;
}
