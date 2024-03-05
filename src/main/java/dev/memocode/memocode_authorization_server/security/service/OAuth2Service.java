package dev.memocode.memocode_authorization_server.security.service;

import dev.memocode.memocode_authorization_server.domain.account.entity.Account;
import dev.memocode.memocode_authorization_server.security.dto.SocialInfo;
import dev.memocode.memocode_authorization_server.security.entity.SecurityAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2Service extends DefaultOAuth2UserService {

    private final OAuth2UserService oAuth2UserService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        SocialInfo socialInfo = SocialInfo.createSocialInfo(
                userRequest.getClientRegistration().getRegistrationId(), super.loadUser(userRequest));

        Account account = oAuth2UserService.findBySocialInfo(socialInfo)
                .orElseGet(() -> oAuth2UserService.createSocialAccount(socialInfo));

        return SecurityAccount.builder()
                .id(account.getId())
                .authority(account.getAuthority())
                .build();
    }
}
