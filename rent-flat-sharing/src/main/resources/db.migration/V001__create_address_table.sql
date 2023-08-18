CREATE SEQUENCE address_info_sequence start 6 increment 1;

CREATE TABLE IF NOT EXISTS address_info(
    id  INT8 PRIMARY KEY NOT NULL,
    city VARCHAR(50),
    house_number VARCHAR(10),
    street VARCHAR (50)
);

INSERT INTO address_info (id, city, house_number, street)
VALUES (1, 'Vidnoye', '4', 'Бульвар Зеленый аллеи'),
 (2, 'Vidnoye', '63к1', 'Улица Адмирала Лазарево'),
 (3, 'Moscow', '21', '2-я Мелитопольская улица'),
 (4, 'Moscow', '5', 'Улица Поляны'),
 (5, 'Moscow', '4к1', 'Улица Маршала Савитского');


