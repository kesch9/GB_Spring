BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO products (title, cost) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('camera', 500);

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Ivan'),
('Petr');

DROP TABLE IF EXISTS products_customers CASCADE;
CREATE TABLE products_customers (product_id bigint, customer_id bigint, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (customer_id) REFERENCES customers (id), cost bigint);
INSERT INTO products_customers (product_id, customer_id, cost) VALUES
(1, 1, 10),
(2, 1, 21),
(3, 1, 100),
(4, 1, 50),
(5, 1, 500),
(1, 2, 11),
(2, 2, 20);

COMMIT;