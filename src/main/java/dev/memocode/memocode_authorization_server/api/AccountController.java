package dev.memocode.memocode_authorization_server.api;

import dev.memocode.memocode_authorization_server.api.spec.AccountApi;
import dev.memocode.memocode_authorization_server.dto.AccountUpdateDTO;
import dev.memocode.memocode_authorization_server.dto.form.AccountUpdateForm;
import dev.memocode.memocode_authorization_server.mapper.AccountUpdateDTOMapper;
import dev.memocode.memocode_authorization_server.usecase.AccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/accounts")
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    private final AccountUseCase accountUseCase;
    private final AccountUpdateDTOMapper accountUpdateDTOMapper;

    @PatchMapping("/{accountId}")
    public ResponseEntity<Void> updateAccount(@RequestBody AccountUpdateForm form, @PathVariable UUID accountId) {

        AccountUpdateDTO accountUpdateDTO = accountUpdateDTOMapper.fromAccountUpdateFormAndAccountId(form, accountId);
        accountUseCase.updateAccount(accountUpdateDTO);

        return ResponseEntity.noContent().build();
    }
}
