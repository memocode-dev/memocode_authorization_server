package dev.memocode.memocode_authorization_server.mapper;

import dev.memocode.memocode_authorization_server.dto.AccountUpdateDTO;
import dev.memocode.memocode_authorization_server.dto.form.AccountUpdateForm;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountUpdateDTOMapper {

    public AccountUpdateDTO fromAccountUpdateFormAndAccountId(AccountUpdateForm form, UUID accountId) {
        return AccountUpdateDTO.builder()
                .authority(form.getAuthority())
                .accountId(accountId)
                .build();
    }
}
