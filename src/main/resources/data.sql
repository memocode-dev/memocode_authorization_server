DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts
(
    id         CHAR(36)     NOT NULL PRIMARY KEY,
    email      VARCHAR(255) NOT NULL UNIQUE,
    auth_id    VARCHAR(255) NOT NULL,
    auth_type  ENUM('KAKAO', 'EMAIL') NOT NULL,
    authority  ENUM('ANONYMOUS', 'USER', 'ADMIN') NOT NULL,
    created_at DATETIME     NOT NULL,
    updated_at DATETIME     NOT NULL,
    deleted_at DATETIME,
    is_deleted BOOLEAN      NOT NULL,
    UNIQUE (auth_id, auth_type)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;