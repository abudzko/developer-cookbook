CREATE TABLE users (
    login varchar PRIMARY KEY,
    password_hash varchar NOT NULL
);
CREATE TABLE refresh_token (
    login varchar PRIMARY KEY,
    refresh_token varchar NOT NULL,
    expire_at timestamp NOT NULL
);
CREATE TABLE token_config (
    login varchar PRIMARY KEY,
    token_private_key bytea NOT NULL,
    token_public_key bytea NOT NULL,
    access_token_life_time_sec int NOT NULL,
    refresh_token_life_time_sec int NOT NULL
);
