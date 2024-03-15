package dev.memocode.memocode_authorization_server.in.api.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2AuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // AuthenticationException -> OAuth2AuthenticationException
        if (exception instanceof OAuth2AuthenticationException oAuth2Exception) {

            log.info("{}", oAuth2Exception);

            String errorCode = oAuth2Exception.getError().getErrorCode();
            String errorMessage = oAuth2Exception.getError().getDescription();
        }

        response.sendRedirect("/login?error=social");
    }
}
