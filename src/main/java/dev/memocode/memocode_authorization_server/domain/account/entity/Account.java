package dev.memocode.memocode_authorization_server.domain.account.entity;

import dev.memocode.memocode_authorization_server.domain.base.entity.AggregateRoot;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"auth_id", "auth_type"})
})
@SQLDelete(sql = "UPDATE accounts SET deleted = true, deleted_at = NOW() WHERE id = ?")
@SQLRestriction("is_deleted = false")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Account extends AggregateRoot {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "auth_id")
    private String authId;

    @Enumerated(STRING)
    @Column(name = "auth_type")
    private AuthType authType;

    @Enumerated(STRING)
    @Column(name = "authority")
    private Authority authority;

    @Column(name = "user_id", unique = true)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID userId;

    public void updateAuthority(Authority authority) {
        this.authority = authority;
    }
}
