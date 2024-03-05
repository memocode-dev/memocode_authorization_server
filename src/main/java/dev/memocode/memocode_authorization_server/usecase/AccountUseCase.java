package dev.memocode.memocode_authorization_server.usecase;

import dev.memocode.memocode_authorization_server.dto.AccountUpdateDTO;

public interface AccountUseCase {

    void updateAccount(AccountUpdateDTO dto);
}
