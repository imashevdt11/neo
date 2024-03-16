CREATE TABLE Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE
);

CREATE TABLE Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    phoneNumber VARCHAR(20)
);

CREATE TABLE Orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT
);

CREATE TABLE OrderProduct (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT
);
ALTER TABLE Orders
    ADD CONSTRAINT fk_customer_id
        FOREIGN KEY (customer_id)
            REFERENCES Customer(id);

ALTER TABLE OrderProduct
    ADD CONSTRAINT fk_order_id
        FOREIGN KEY (order_id)
            REFERENCES Orders(id);

ALTER TABLE OrderProduct
    ADD CONSTRAINT fk_product_id
        FOREIGN KEY (product_id)
            REFERENCES Product(id);
