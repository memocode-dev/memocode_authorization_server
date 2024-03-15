package dev.memocode.memocode_authorization_server.in.api.controller;

import dev.memocode.memocode_authorization_server.in.api.form.TokenCreateForm;
import dev.memocode.memocode_authorization_server.in.api.spec.TokenApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenController implements TokenApi {

    @Value("${spring.security.oauth2.authorizationserver.client.public-client.registration.client-secret}")
    private String clientSecret;

    @PostMapping
    public ResponseEntity<Map> getToken(
            @RequestBody TokenCreateForm form,
            @CookieValue(name = "ff_rt", required = false) String refresh_token
            ) {

        form.setClient_secret(clientSecret.replace("{noop}", ""));
        form.setRefresh_token(refresh_token);

        ResponseEntity<Map> responseEntity = getToken(form);

        HttpStatusCode statusCode = responseEntity.getStatusCode();
        Map body = responseEntity.getBody();

        if (statusCode.is2xxSuccessful()) {
            ResponseCookie cookie = createCookie((String) body.get("refresh_token"), -1);

            body.remove("refresh_token");

            return ResponseEntity
                    .status(statusCode)
                    .header(SET_COOKIE, cookie.toString())
                    .body(body);
        }

        return ResponseEntity
                .status(statusCode)
                .body(body);
    }

    @DeleteMapping("/refresh")
    public ResponseEntity<Map> removeRefreshToken () {
        return ResponseEntity
                .noContent()
                .header(SET_COOKIE, createCookie("", 0).toString())
                .build();
    }

    private static ResponseCookie createCookie(String body, int maxAgeSeconds) {
        return ResponseCookie.from("ff_rt", body)
                .httpOnly(true)
                .path("/")
                .secure(true)
                .sameSite("None")
                .maxAge(maxAgeSeconds)
                .build();
    }

    private ResponseEntity<Map> getToken(TokenCreateForm form) {
        return WebClient.create("http://localhost:9000")
                .post()
                .uri("/oauth2/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(form.toFormData())
                .exchangeToMono(response -> {
                    if (response.statusCode().isError()) {
                        return response.bodyToMono(Map.class)
                                .flatMap(body -> Mono.just(ResponseEntity.status(response.statusCode()).body(body)));
                    } else {
                        return response.toEntity(Map.class);
                    }
                })
                .block();
    }
}
