package dev.memocode.memocode_authorization_server.usecase;

import dev.memocode.memocode_authorization_server.dto.AccountUpdateDTO;
import jakarta.validation.Valid;

public interface AccountUseCase {

    void updateAccount(@Valid AccountUpdateDTO dto);
}
