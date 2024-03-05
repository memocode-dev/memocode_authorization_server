package dev.memocode.memocode_authorization_server.web;

import dev.memocode.memocode_authorization_server.security.entity.SecurityAccount;
import dev.memocode.memocode_authorization_server.web.spec.HomeApi;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController implements HomeApi {

    @GetMapping
    public String showHomePage(@AuthenticationPrincipal SecurityAccount user) {
        if (user == null) {
            return "인증 안됨";
        }

        return "userId : %s".formatted(user.getId());
    }
}
