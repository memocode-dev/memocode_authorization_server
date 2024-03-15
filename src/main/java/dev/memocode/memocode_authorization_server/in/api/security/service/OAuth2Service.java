package dev.memocode.memocode_authorization_server.in.api.security.service;

import dev.memocode.memocode_authorization_server.domain.account.dto.AccountDTO;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountFindOrCreateDTO;
import dev.memocode.memocode_authorization_server.domain.account.mapper.AccountMapper;
import dev.memocode.memocode_authorization_server.in.api.security.dto.SocialInfo;
import dev.memocode.memocode_authorization_server.in.api.security.entity.SecurityAccount;
import dev.memocode.memocode_authorization_server.usecase.AccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2Service extends DefaultOAuth2UserService {

    private final AccountUseCase accountUseCase;

    private final AccountMapper accountMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        SocialInfo socialInfo = SocialInfo.createSocialInfo(
                userRequest.getClientRegistration().getRegistrationId(), super.loadUser(userRequest));

        AccountFindOrCreateDTO accountFindOrCreateDTO = accountMapper.socialInfo_to_AccountFindOrCreateDTO(socialInfo);

        AccountDTO account = accountUseCase.findAccountOrCreateAccount(accountFindOrCreateDTO);

        return SecurityAccount.builder()
                .account(account)
                .build();
    }
}
