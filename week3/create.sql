CREATE DATABASE store;

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE
);

INSERT INTO product(name, price)
VALUES ('hockey mask', 77.7),
       ('machete', 67.7),
       ('glows', 57.7);
