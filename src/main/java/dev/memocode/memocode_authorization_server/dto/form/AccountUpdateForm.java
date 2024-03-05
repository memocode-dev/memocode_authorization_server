package dev.memocode.memocode_authorization_server.dto.form;

import dev.memocode.memocode_authorization_server.domain.account.entity.Authority;
import lombok.Data;

@Data
public class AccountUpdateForm {
    private Authority authority;
}
