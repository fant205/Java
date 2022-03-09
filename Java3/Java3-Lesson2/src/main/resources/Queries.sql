CREATE DATABASE java3;

CREATE TABLE users (
    login varchar(20),
    password varchar(20),
    nickname varchar(20)
)

INSERT INTO users (login, password,nickname) VALUES ('login1','password1', 'first_user');
INSERT INTO users (login, password,nickname) VALUES ('login2','password2', 'second_user');
INSERT INTO users (login, password,nickname) VALUES ('login3','password3', 'third_user');
