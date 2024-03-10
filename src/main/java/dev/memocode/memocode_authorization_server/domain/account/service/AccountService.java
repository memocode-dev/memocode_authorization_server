package dev.memocode.memocode_authorization_server.domain.account.service;

import dev.memocode.memocode_authorization_server.domain.account.entity.Account;
import dev.memocode.memocode_authorization_server.domain.account.entity.AuthType;
import dev.memocode.memocode_authorization_server.domain.account.repository.AccountRepository;
import dev.memocode.memocode_authorization_server.dto.AccountCreateDTO;
import dev.memocode.memocode_authorization_server.dto.AccountUpdateDTO;
import dev.memocode.memocode_authorization_server.exception.GlobalErrorCode;
import dev.memocode.memocode_authorization_server.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Optional<Account> findByAuthIdAndAuthType(String authId, AuthType authType) {
        return accountRepository.findByAuthIdAndAuthType(authId, authType);
    }

    public Optional<Account> findById(UUID accountId) {
        return accountRepository.findById(accountId);
    }

    public Account findByIdElseThrow(UUID accountId) {
        return findById(accountId)
                .orElseThrow(() -> new GlobalException(GlobalErrorCode.ACCOUNT_AUTHORITY_NOT_FOUND));
    }

    public Account createAccount(AccountCreateDTO dto) {
        Account account = Account.builder()
                .authType(dto.getType())
                .authId(dto.getAuthId())
                .email(dto.getEmail())
                .authority(dto.getAuthority())
                .build();

        return accountRepository.save(account);
    }

    public void updateAccount(AccountUpdateDTO dto) {
        Account account = findByIdElseThrow(dto.getAccountId());

        account.updateAuthority(dto.getAuthority());
    }
}
