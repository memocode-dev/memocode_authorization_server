package dev.memocode.memocode_authorization_server.usecase;

import dev.memocode.memocode_authorization_server.domain.account.dto.AccountDTO;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountFindOrCreateDTO;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountUpdateAfterSignupDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface AccountUseCase {

    void updateAccountAfterSignup(@Valid AccountUpdateAfterSignupDTO dto);

    AccountDTO findAccountOrCreateAccount(@Valid AccountFindOrCreateDTO dto);

    AccountDTO findAccountById(@NotNull(message = "ACCOUNT_ID_NOT_NULL:account_id must not be null") UUID accountId);
}
