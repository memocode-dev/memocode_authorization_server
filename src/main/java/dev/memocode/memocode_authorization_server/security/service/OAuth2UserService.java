package dev.memocode.memocode_authorization_server.security.service;

import dev.memocode.memocode_authorization_server.domain.account.entity.Account;
import dev.memocode.memocode_authorization_server.domain.account.entity.Authority;
import dev.memocode.memocode_authorization_server.domain.account.service.AccountService;
import dev.memocode.memocode_authorization_server.dto.AccountCreateDTO;
import dev.memocode.memocode_authorization_server.mapper.AccountCreateDTOMapper;
import dev.memocode.memocode_authorization_server.security.dto.SocialInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dev.memocode.memocode_authorization_server.domain.account.entity.Authority.ANONYMOUS;

@Service
@RequiredArgsConstructor
public class OAuth2UserService {

    private static final Authority DEFAULT_AUTHORITY = ANONYMOUS;

    private final AccountService accountService;

    private final AccountCreateDTOMapper accountCreateDTOMapper;

    public Optional<Account> findBySocialInfo(final SocialInfo socialInfo) {
        return accountService.findByAuthIdAndAuthType(socialInfo.getId(), socialInfo.getType());
    }

    public Account createSocialAccount(final SocialInfo socialInfo) {
        AccountCreateDTO accountCreateDTO = accountCreateDTOMapper.fromSocialInfo(socialInfo, DEFAULT_AUTHORITY);

        return accountService.createAccount(accountCreateDTO);
    }
}
