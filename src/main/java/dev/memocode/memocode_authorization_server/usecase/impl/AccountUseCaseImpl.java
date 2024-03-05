package dev.memocode.memocode_authorization_server.usecase.impl;

import dev.memocode.memocode_authorization_server.domain.account.service.AccountService;
import dev.memocode.memocode_authorization_server.dto.AccountUpdateDTO;
import dev.memocode.memocode_authorization_server.usecase.AccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AccountUseCaseImpl implements AccountUseCase {

    private final AccountService accountService;

    @Override
    public void updateAccount(AccountUpdateDTO dto) {

        accountService.updateAccount(dto);
    }
}
