package dev.memocode.memocode_authorization_server.security.config;

import dev.memocode.memocode_authorization_server.domain.account.entity.Account;
import dev.memocode.memocode_authorization_server.domain.account.service.AccountService;
import dev.memocode.memocode_authorization_server.security.entity.SecurityAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import static dev.memocode.memocode_authorization_server.domain.account.entity.Authority.*;

@Configuration
@RequiredArgsConstructor
public class CustomClaimsConfiguration {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String iss;

    private final AccountService accountService;

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        return (context) -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {

                String clientName = context.getRegisteredClient().getClientName();

                if (clientName.equals("authorization-server")) {
                    context.getClaims().claims((claims) -> {
                        claims.put("scope", "write:log");
                    });
                }

                if (clientName.equals("user-server")) {
                    context.getClaims().claims((claims) -> {
                        claims.put("scope", "write:log write:account");
                    });
                }

                if (clientName.equals("public-client")) {
                    Authentication authentication = context.getPrincipal();

                    SecurityAccount securityAccount = (SecurityAccount) authentication.getPrincipal();

                    Account account = accountService.findByIdElseThrow(securityAccount.getId());

                    if (account.getAuthority() == ANONYMOUS) {
                        context.getClaims().claims((claims) -> {
                            claims.put("scope", "write:anonymous-user");
                        });
                    }

                    if (account.getAuthority() == USER) {
                        context.getClaims().claims((claims) -> {
                            claims.put("scope", "read:me-user");
                        });
                    }

                    if (account.getAuthority() == ADMIN) {
                        context.getClaims().claims((claims) -> {
                            claims.put("scope", "read:user write:user write:organization read:me-user");
                        });
                    }

                    context.getClaims().claims((claims) -> {
                        claims.put("account_id", account.getId().toString());
                        claims.put("authority", account.getAuthority().name().toUpperCase());
                    });
                }

                context.getClaims().claims((claims) -> {
                    claims.put("iss", iss);
                });
            }
        };
    }
}
