--CREATE SCHEMA IF NOT EXISTS app_security;
CREATE TABLE IF NOT EXISTS app_security.users (
  id BIGINT NOT NULL,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  enabled INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS app_security.authorities (
  id BIGINT NOT NULL,
  username VARCHAR(45) NOT NULL,
  authority VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO app_security.authorities VALUES (1, 'user', 'read');
INSERT INTO app_security.users VALUES (1, 'user', 'password', '1');
