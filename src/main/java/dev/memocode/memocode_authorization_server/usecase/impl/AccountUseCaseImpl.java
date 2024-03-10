package dev.memocode.memocode_authorization_server.usecase.impl;

import dev.memocode.memocode_authorization_server.domain.account.service.AccountService;
import dev.memocode.memocode_authorization_server.dto.AccountUpdateDTO;
import dev.memocode.memocode_authorization_server.usecase.AccountUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@Transactional
@RequiredArgsConstructor
public class AccountUseCaseImpl implements AccountUseCase {

    private final AccountService accountService;

    @Override
    public void updateAccount(@Valid AccountUpdateDTO dto) {
        accountService.updateAccount(dto);
    }
}
