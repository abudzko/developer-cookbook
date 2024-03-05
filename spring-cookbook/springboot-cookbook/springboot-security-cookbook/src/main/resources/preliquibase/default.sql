-- By default liquibase put its table in default(public scheme)
-- To put liquibase table together with app table we have to create scheme before liquibase was started
-- See doc of preliquibase-spring-boot-starter
CREATE SCHEMA IF NOT EXISTS app_security;
