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