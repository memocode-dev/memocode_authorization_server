package dev.memocode.memocode_authorization_server.domain.account.mapper;

import dev.memocode.memocode_authorization_server.domain.account.dto.AccountCreateDTO;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountDTO;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountFindOrCreateDTO;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountUpdateAfterSignupDTO;
import dev.memocode.memocode_authorization_server.domain.account.entity.Account;
import dev.memocode.memocode_authorization_server.in.api.security.dto.SocialInfo;
import dev.memocode.memocode_authorization_server.in.kafka.event.UserCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountDTO entityToDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .userId(account.getUserId())
                .email(account.getEmail())
                .authId(account.getAuthId())
                .authType(account.getAuthType())
                .authority(account.getAuthority())
                .deletedAt(account.getDeletedAt())
                .deleted(account.getDeleted())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }

    public AccountFindOrCreateDTO socialInfo_to_AccountFindOrCreateDTO(final SocialInfo socialInfo) {
        return AccountFindOrCreateDTO.builder()
                .authId(socialInfo.getId())
                .email(socialInfo.getEmail())
                .type(socialInfo.getType())
                .build();
    }

    public AccountUpdateAfterSignupDTO userCreatedEvent_to_accountUpdateDTO(UserCreatedEvent userCreatedEvent) {
        return AccountUpdateAfterSignupDTO.builder()
                .userId(userCreatedEvent.getUserId())
                .build();
    }

    public AccountCreateDTO accountFindOrCreateDTO_to_accountCreateDTO(AccountFindOrCreateDTO dto) {
        return AccountCreateDTO.builder()
                .authId(dto.getAuthId())
                .email(dto.getEmail())
                .type(dto.getType())
                .build();
    }
}
