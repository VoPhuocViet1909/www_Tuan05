-- =========================================
-- 1. Create Database
-- =========================================
DROP DATABASE IF EXISTS shoppingdb;
CREATE DATABASE shoppingdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE shoppingdb;

-- =========================================
-- 2. Create TABLES in dependency order
-- =========================================

-- CATEGORY (independent)
CREATE TABLE category (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

-- CUSTOMER (independent)
CREATE TABLE customer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          customer_since DATE
);

-- PRODUCT (depends on category)
CREATE TABLE product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DECIMAL(19,2),
                         in_stock BOOLEAN,
                         category_id INT,
                         CONSTRAINT fk_product_category FOREIGN KEY (category_id)
                             REFERENCES category(id)
                             ON DELETE SET NULL ON UPDATE CASCADE
);

-- COMMENT (depends on product)
CREATE TABLE comment (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         text VARCHAR(255),
                         product_id INT,
                         CONSTRAINT fk_comment_product FOREIGN KEY (product_id)
                             REFERENCES product(id)
                             ON DELETE CASCADE ON UPDATE CASCADE
);

-- ORDERS (depends on customer)
CREATE TABLE orders (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        date DATE,
                        customer_id INT,
                        CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id)
                            REFERENCES customer(id)
                            ON DELETE CASCADE ON UPDATE CASCADE
);

-- ORDER_LINE (depends on orders + product)
CREATE TABLE order_line (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            order_id INT,
                            product_id INT,
                            amount INT,
                            purchase_price DECIMAL(19,2),
                            CONSTRAINT fk_orderline_order FOREIGN KEY (order_id)
                                REFERENCES orders(id)
                                ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT fk_orderline_product FOREIGN KEY (product_id)
                                REFERENCES product(id)
                                ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================
-- 3. Insert Sample Data
-- =========================================

-- Category
INSERT INTO category (name) VALUES
                                ('Laptops'), ('Phones'), ('Accessories');

-- Customer
INSERT INTO customer (name, customer_since) VALUES
                                                ('Alice Johnson', '2023-01-10'),
                                                ('Bob Smith', '2022-09-05');

-- Product
INSERT INTO product (name, price, in_stock, category_id) VALUES
                                                             ('Dell XPS 13', 25000.00, TRUE, 1),
                                                             ('iPhone 15 Pro Max', 32999.00, TRUE, 2),
                                                             ('Sony WH-1000XM5', 7990.00, TRUE, 3),
                                                             ('Keychron K6 Keyboard', 3500.00, TRUE, 3);

-- Comment
INSERT INTO comment (text, product_id) VALUES
                                           ('Great performance and lightweight design!', 1),
                                           ('Battery life could be better.', 1),
                                           ('Camera quality is excellent.', 2),
                                           ('Superb sound quality!', 3);

-- Orders
INSERT INTO orders (date, customer_id) VALUES
                                           ('2024-09-01', 1),
                                           ('2024-09-05', 2);

-- Order Lines
INSERT INTO order_line (order_id, product_id, amount, purchase_price) VALUES
                                                                          (1, 1, 1, 25000.00),
                                                                          (1, 3, 2, 7990.00),
                                                                          (2, 2, 1, 32999.00);

-- =========================================
-- ✅ Done! Verify Data
-- =========================================
SELECT 'CATEGORY' AS TableName, COUNT(*) AS RowsCount FROM category
UNION ALL
SELECT 'CUSTOMER', COUNT(*) FROM customer
UNION ALL
SELECT 'PRODUCT', COUNT(*) FROM product
UNION ALL
SELECT 'COMMENT', COUNT(*) FROM comment
UNION ALL
SELECT 'ORDERS', COUNT(*) FROM orders
UNION ALL
SELECT 'ORDER_LINE', COUNT(*) FROM order_line;
