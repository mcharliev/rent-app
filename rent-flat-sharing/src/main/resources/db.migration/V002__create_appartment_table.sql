CREATE SEQUENCE apartment_info_sequence start 6 increment 1;

CREATE TABLE IF NOT EXISTS apartment_info
(
    id           INT8 PRIMARY KEY NOT NULL,
    count_rooms  VARCHAR(10),
    full_rating  VARCHAR(10),
    price        VARCHAR(50),
    time_reg_lot TIMESTAMP,
    image        VARCHAR(255),
    address_id   INT8 REFERENCES address_info (id)
);


INSERT INTO apartment_info (id, count_rooms, address_id, full_rating, price)
VALUES (1, '2', 1, '5', '6500000'),
       (2, '3', 2, '8', '17500000'),
       (3, '1', 3, '8', '7500000'),
       (4, '2', 4, '7', '13800000'),
       (5, '1', 5, '6', '7750000');