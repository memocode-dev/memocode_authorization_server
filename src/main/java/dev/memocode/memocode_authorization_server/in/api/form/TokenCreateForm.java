package dev.memocode.memocode_authorization_server.in.api.form;

import lombok.Data;
import lombok.ToString;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@ToString
public class TokenCreateForm {
    private String grant_type;
    private String code;
    private String refresh_token;
    private String redirect_uri;
    private String code_verifier;
    private String client_id;
    private String client_secret;

    public MultiValueMap<String, String> toFormData() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        if (this.grant_type != null) {
            formData.add("grant_type", this.grant_type);
        }

        if (this.code != null) {
            formData.add("code", this.code);
        }

        if (this.refresh_token != null) {
            formData.add("refresh_token", this.refresh_token);
        }

        if (this.redirect_uri != null) {
            formData.add("redirect_uri", this.redirect_uri);
        }

        if (this.code_verifier != null) {
            formData.add("code_verifier", this.code_verifier);
        }

        if (this.client_id != null) {
            formData.add("client_id", this.client_id);
        }

        if (this.client_secret != null) {
            formData.add("client_secret", this.client_secret);
        }

        return formData;
    }
}
