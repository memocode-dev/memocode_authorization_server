package dev.memocode.memocode_authorization_server.domain.account.repository;


import dev.memocode.memocode_authorization_server.domain.account.entity.Account;
import dev.memocode.memocode_authorization_server.domain.account.entity.AuthType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByAuthIdAndAuthType(String authId, AuthType authType);

    Optional<Account> findByUserId(UUID userId);
}
