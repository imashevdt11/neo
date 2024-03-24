CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE
);

CREATE TABLE customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    phone_number VARCHAR(20)
);

CREATE TABLE purchase (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    customer_id BIGINT,
    quantity INT
);

ALTER TABLE purchase
    ADD CONSTRAINT fk_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES Customer(id);

ALTER TABLE purchase
    ADD CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES product(id);
