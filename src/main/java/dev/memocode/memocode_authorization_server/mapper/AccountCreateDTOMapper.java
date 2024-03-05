package dev.memocode.memocode_authorization_server.mapper;

import dev.memocode.memocode_authorization_server.domain.account.entity.Authority;
import dev.memocode.memocode_authorization_server.dto.AccountCreateDTO;
import dev.memocode.memocode_authorization_server.security.dto.SocialInfo;
import org.springframework.stereotype.Component;

@Component
public class AccountCreateDTOMapper {

    public AccountCreateDTO fromSocialInfo(final SocialInfo socialInfo, Authority authority) {
        return AccountCreateDTO.builder()
                .authId(socialInfo.getId())
                .email(socialInfo.getEmail())
                .type(socialInfo.getType())
                .authority(authority)
                .build();
    }
}
