package dev.memocode.memocode_authorization_server.domain.account.service;

import dev.memocode.memocode_authorization_server.domain.account.dto.AccountCreateDTO;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountDTO;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountFindOrCreateDTO;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountUpdateAfterSignupDTO;
import dev.memocode.memocode_authorization_server.domain.account.entity.Account;
import dev.memocode.memocode_authorization_server.domain.account.entity.AuthType;
import dev.memocode.memocode_authorization_server.domain.account.entity.Authority;
import dev.memocode.memocode_authorization_server.domain.account.mapper.AccountMapper;
import dev.memocode.memocode_authorization_server.domain.account.repository.AccountRepository;
import dev.memocode.memocode_authorization_server.domain.exception.GlobalException;
import dev.memocode.memocode_authorization_server.usecase.AccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

import static dev.memocode.memocode_authorization_server.domain.account.entity.Authority.ANONYMOUS;
import static dev.memocode.memocode_authorization_server.domain.account.entity.Authority.USER;
import static dev.memocode.memocode_authorization_server.domain.exception.GlobalErrorCode.ACCOUNT_NOT_FOUND;

@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class AccountService implements AccountUseCase {

    private static final Authority DEFAULT_AUTHORITY = ANONYMOUS;
    private static final Authority DEFAULT_AUTHORITY_AFTER_SIGNUP = USER;

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public void updateAccountAfterSignup(AccountUpdateAfterSignupDTO dto) {
        Account account = findByUserIdElseThrow(dto.getUserId());
        account.updateAuthority(DEFAULT_AUTHORITY_AFTER_SIGNUP);
    }

    @Override
    public AccountDTO findAccountOrCreateAccount(AccountFindOrCreateDTO dto) {
        Account account = findByAuthIdAndAuthType(dto.getAuthId(), dto.getType())
                .orElseGet(() -> {
                    AccountCreateDTO accountCreateDTO = accountMapper.accountFindOrCreateDTO_to_accountCreateDTO(dto);
                    return createAccount(accountCreateDTO);
                });

        return accountMapper.entityToDTO(account);
    }

    @Override
    public AccountDTO findAccountById(UUID accountId) {
        Account account = findByIdElseThrow(accountId);

        return accountMapper.entityToDTO(account);
    }

    private Account findByIdElseThrow(UUID accountId) {
        return findById(accountId)
                .orElseThrow(() -> new GlobalException(ACCOUNT_NOT_FOUND));
    }

    private Account createAccount(AccountCreateDTO dto) {
        Account account = Account.builder()
                .authType(dto.getType())
                .authId(dto.getAuthId())
                .email(dto.getEmail())
                .userId(UUID.randomUUID())
                .authority(DEFAULT_AUTHORITY)
                .build();

        return accountRepository.save(account);
    }

    private Optional<Account> findByAuthIdAndAuthType(String authId, AuthType authType) {
        return accountRepository.findByAuthIdAndAuthType(authId, authType);
    }

    private Optional<Account> findById(UUID accountId) {
        return accountRepository.findById(accountId);
    }

    private Optional<Account> findByUserId(UUID userId) {
        return accountRepository.findByUserId(userId);
    }

    public Account findByUserIdElseThrow(UUID userId) {
        return findByUserId(userId)
                .orElseThrow(() -> new GlobalException(ACCOUNT_NOT_FOUND));
    }
}
