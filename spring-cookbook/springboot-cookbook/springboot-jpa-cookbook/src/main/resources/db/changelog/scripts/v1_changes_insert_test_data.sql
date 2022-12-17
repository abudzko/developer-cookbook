CREATE TABLE user(
    id varchar PRIMARY KEY,
    name varchar
);
CREATE TABLE car(
    car_number varchar PRIMARY KEY,
    user_id varchar,
    CONSTRAINT car_user_id_fk
        FOREIGN KEY (user_id)
        REFERENCES user(id)
);
CREATE TABLE password(
    id varchar PRIMARY KEY,
    user_id varchar,
    password varchar NOT NULL,
    CONSTRAINT password_user_id_fk
        FOREIGN KEY (user_id)
        REFERENCES user(id)
        ON DELETE CASCADE
);

CREATE TABLE passport(
    id varchar PRIMARY KEY,
    user_id varchar,
    number varchar NOT NULL,
    CONSTRAINT passport_user_id_fk
        FOREIGN KEY (user_id)
        REFERENCES user(id)
        ON DELETE CASCADE
);

CREATE TABLE user_comment(
    id varchar PRIMARY KEY,
    user_id varchar,
    CONSTRAINT user_comment_user_id_fk
        FOREIGN KEY (user_id)
        REFERENCES user(id)
        ON DELETE CASCADE
);
