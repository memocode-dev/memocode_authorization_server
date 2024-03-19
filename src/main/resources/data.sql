DROP TABLE IF EXISTS oauth2_authorization;
DROP TABLE IF EXISTS oauth2_authorization_consent;
DROP TABLE IF EXISTS accounts;

CREATE TABLE oauth2_authorization
(
    id                            CHAR(36)     NOT NULL,
    registered_client_id          varchar(100) NOT NULL,
    principal_name                varchar(200) NOT NULL,
    authorization_grant_type      varchar(100) NOT NULL,
    authorized_scopes             varchar(1000) DEFAULT NULL,
    attributes                    TEXT          DEFAULT NULL,
    state                         varchar(500)  DEFAULT NULL,
    authorization_code_value      TEXT          DEFAULT NULL,
    authorization_code_issued_at  timestamp     DEFAULT NULL,
    authorization_code_expires_at timestamp     DEFAULT NULL,
    authorization_code_metadata   TEXT          DEFAULT NULL,
    access_token_value            TEXT          DEFAULT NULL,
    access_token_issued_at        timestamp     DEFAULT NULL,
    access_token_expires_at       timestamp     DEFAULT NULL,
    access_token_metadata         TEXT          DEFAULT NULL,
    access_token_type             varchar(100)  DEFAULT NULL,
    access_token_scopes           varchar(1000) DEFAULT NULL,
    oidc_id_token_value           TEXT          DEFAULT NULL,
    oidc_id_token_issued_at       timestamp     DEFAULT NULL,
    oidc_id_token_expires_at      timestamp     DEFAULT NULL,
    oidc_id_token_metadata        TEXT          DEFAULT NULL,
    refresh_token_value           TEXT          DEFAULT NULL,
    refresh_token_issued_at       timestamp     DEFAULT NULL,
    refresh_token_expires_at      timestamp     DEFAULT NULL,
    refresh_token_metadata        TEXT          DEFAULT NULL,
    user_code_value               TEXT          DEFAULT NULL,
    user_code_issued_at           timestamp     DEFAULT NULL,
    user_code_expires_at          timestamp     DEFAULT NULL,
    user_code_metadata            TEXT          DEFAULT NULL,
    device_code_value             TEXT          DEFAULT NULL,
    device_code_issued_at         timestamp     DEFAULT NULL,
    device_code_expires_at        timestamp     DEFAULT NULL,
    device_code_metadata          TEXT          DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE oauth2_authorization_consent
(
    registered_client_id varchar(100)  NOT NULL,
    principal_name       varchar(200)  NOT NULL,
    authorities          varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE accounts
(
    id         CHAR(36)     NOT NULL PRIMARY KEY,
    email      VARCHAR(255) NOT NULL UNIQUE,
    auth_id    VARCHAR(255) NOT NULL,
    auth_type  ENUM('KAKAO', 'EMAIL') NOT NULL,
    authority  ENUM('ANONYMOUS', 'USER', 'ADMIN') NOT NULL,
    user_id    CHAR(36)     NOT NULL UNIQUE,
    created_at DATETIME     NOT NULL,
    updated_at DATETIME     NOT NULL,
    deleted_at DATETIME,
    is_deleted BOOLEAN      NOT NULL,
    UNIQUE (auth_id, auth_type)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE outbox
(
    id             CHAR(36) PRIMARY KEY,
    aggregate_id   CHAR(36) NOT NULL,
    aggregate_type ENUM('ACCOUNT') NOT NULL,
    event_type     ENUM('ACCOUNT_AUTHORITY_UPDATED') NOT NULL,
    payload        JSON     NOT NULL,
    created_at     DATETIME NOT NULL,
    updated_at     DATETIME NOT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;