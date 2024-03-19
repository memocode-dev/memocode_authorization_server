package dev.memocode.memocode_authorization_server.out.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "`oauth2_authorization_consent`")
@IdClass(AuthorizationConsent.AuthorizationConsentId.class)
public class AuthorizationConsent {
    @Id
    private String registeredClientId;
    @Id
    private String principalName;
    private String authorities;

    @Getter
    @Setter
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class AuthorizationConsentId implements Serializable {
        @EqualsAndHashCode.Include
        private String registeredClientId;
        @EqualsAndHashCode.Include
        private String principalName;
    }
}
