create database RevshopP1ProjectDevelopementDatabase;
use RevshopP1ProjectDevelopementDatabase;
SELECT* from seller;
update  seller set total_earning=78400,total_item_sold=11562,current_month_earning=10531,current_month_item_sold=673 where seller_id=1;
insert into category (category_name) values("Phone");
insert into category (category_name) values("Laptops");
insert into category (category_name) values("Tabs");
insert into category (category_name) values("Pods");

-- Inserting brands for Phones
INSERT INTO brand (brand_name, category_id) VALUES ("Apple", 1);
INSERT INTO brand (brand_name, category_id) VALUES ("Samsung", 1);
INSERT INTO brand (brand_name, category_id) VALUES ("OnePlus", 1);
INSERT INTO brand (brand_name, category_id) VALUES ("Google", 1);
INSERT INTO brand (brand_name, category_id) VALUES ("Xiaomi", 1);

-- Inserting brands for Laptops
INSERT INTO brand (brand_name, category_id) VALUES ("Dell", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("HP", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("Lenovo", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("Asus", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("Acer", 2);

-- Inserting brands for Tabs
INSERT INTO brand (brand_name, category_id) VALUES ("Apple", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Samsung", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Microsoft", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Amazon", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Lenovo", 3);

-- Inserting brands for Pods
INSERT INTO brand (brand_name, category_id) VALUES ("Apple", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Samsung", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Sony", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Jabra", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Bose", 4);

select * from product;
select * from brand;
select * from color;
select * from product_image;
-- 1
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Awesome T-shirt', 'A cool t-shirt with multiple colors', 20.99, 100, 10, 15.00, 2, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Red', 'https://example.com/red.jpg', 1),
    ('Green', 'https://example.com/green.jpg', 1),
    ('Blue', 'https://example.com/blue.jpg', 1);
INSERT INTO productimage (image_url, category_id, product_id) 
VALUES 
    ('https://example.com/image1.jpg', 3, 1),
    ('https://example.com/image2.jpg', 3, 1);


drop database RevshopP1ProjectDevelopementDatabase;