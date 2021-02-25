BEGIN;

DROP TABLE IF EXISTS product_items CASCADE;
CREATE TABLE product_items (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO product_items (title, cost) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('camera', 500);

COMMIT;