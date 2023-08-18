CREATE SEQUENCE user_info_sequence start 1 increment 1;

CREATE TABLE IF NOT EXISTS user_info
(
    id          INT8 PRIMARY KEY NOT NULL,
    username     VARCHAR(50),
    password    VARCHAR(300),
    year_of_birth int,
    role        varchar(20)
);

-- INSERT INTO user_info (id, username, password, year_of_birth)
-- VALUES (1, 'first@mail.ru', 'password', 1995),
--        (2, 'second@mail.ru', 'password', 2001),
--        (3, 'third@mail.ru', 'password', 1997),
--        (4, 'fourth@mail.ru', 'password', 1999),
--        (5, 'fifth@mail.ru', 'password', 2002);