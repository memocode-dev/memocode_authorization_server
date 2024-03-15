package dev.memocode.memocode_authorization_server.in.web.spec;

import dev.memocode.memocode_authorization_server.in.api.security.entity.SecurityAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "home", description = "홈")
@SecurityRequirement(name = "bearer-key")
public interface HomeApi {

    @Operation(summary = "홈")
    String showHomePage(SecurityAccount user);
}
