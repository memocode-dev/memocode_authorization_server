package dev.memocode.memocode_authorization_server.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2AccountService {

    private final AccountService accountService;
}
