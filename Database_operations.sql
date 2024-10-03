create database RevshopP1ProjectDevelopementDatabase;
use RevshopP1ProjectDevelopementDatabase;
-- after install the project execute below codes

INSERT INTO seller (
    business_address,
    business_phone_number,
    current_month_earning,
    current_month_item_sold,
    email,
    home_address,
    name,
    pan_number,
    password,
    personal_phone_number,
    total_earning,
    total_item_sold
) VALUES (
    '123 Business St, City',       -- business_address
    8790431602,                    -- business_phone_number
    0,                      -- current_month_earning
    0,                           -- current_month_item_sold
    'Guna15081947@gmail.com',         -- email
    '456 Home St, City',           -- home_address
    'Guna',                    -- name
    'ABCDE1234F',                  -- pan_number
    'apple',                 -- password
    8790431602,                    -- personal_phone_number
    0,                      -- total_earning
    0                           -- total_item_sold
);

update  seller set total_earning=78400,total_item_sold=11562,current_month_earning=10531,current_month_item_sold=673 where seller_id=1;
insert into category (category_name) values("Phone");
insert into category (category_name) values("Laptops");
insert into category (category_name) values("Tabs");
insert into category (category_name) values("Pods");
-- Inserting brands for Phones
INSERT INTO brand (brand_name, category_id) VALUES ("Apple", 1);
INSERT INTO brand (brand_name, category_id) VALUES ("Samsung", 1);
INSERT INTO brand (brand_name, category_id) VALUES ("OnePlus", 1);
INSERT INTO brand (brand_name, category_id) VALUES ("iQOO", 1);
INSERT INTO brand (brand_name, category_id) VALUES ("Xiaomi", 1);
INSERT INTO brand (brand_name, category_id) VALUES ("Realme", 1);

-- Inserting brands for Laptops
INSERT INTO brand (brand_name, category_id) VALUES ("Apple", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("Dell", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("HP", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("Lenovo", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("ASUS", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("Microsoft", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("Acer", 2);
INSERT INTO brand (brand_name, category_id) VALUES ("Razer", 2);

-- Inserting brands for Tabs
INSERT INTO brand (brand_name, category_id) VALUES ("Apple", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Samsung", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Microsoft", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Lenovo", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Huawei", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Amazon", 3);
INSERT INTO brand (brand_name, category_id) VALUES ("Xiaomi", 3);

-- Inserting brands for Pods
INSERT INTO brand (brand_name, category_id) VALUES ("Apple", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Samsung", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Sony", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Bose", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Jabra", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Sennheiser", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Beats", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Anker", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("Google", 4);
INSERT INTO brand (brand_name, category_id) VALUES ("JBL", 4);




-- 1.Phones

-- Product 1: iPhone 15 Pro
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('iPhone 15 Pro', 'Latest Apple iPhone with A17 Pro chip and Titanium frame.', 1199.99, 50, 5, 10.00, 1, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Titanium Blue', 'https://m.media-amazon.com/images/I/11fW1UmKueL._AC_SL1500_.jpg', 1),
    ('Titanium White', 'https://m.media-amazon.com/images/I/01wjNKcMMcL._AC_SL1500_.jpg', 1),
    ('Titanium Black', 'https://m.media-amazon.com/images/I/11lmjtsHP6L._AC_SL1500_.jpg', 1),
    ('Titanium Natural', 'https://m.media-amazon.com/images/I/11DhTg0wucL._AC_SL1500_.jpg', 1);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71g7dxYXiOL._SX522_.jpg', 1, 1),
    ('https://m.media-amazon.com/images/I/81+GIkwqLIL._SX522_.jpg', 1, 1),
    ('https://m.media-amazon.com/images/I/71lmRVkniLL._SX522_.jpg', 1, 1),
    ('https://m.media-amazon.com/images/I/61VjVVHbvHL._SX522_.jpg', 1, 1);

-- Product 2: Samsung Galaxy S24 Ultra
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Samsung Galaxy S24 Ultra', 'Cutting-edge Samsung Galaxy with powerful camera features.', 1299.99, 70, 10, 15.00, 2, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Titanium Violet', 'https://m.media-amazon.com/images/I/41LLw6mmoiL._AC_SL1500_.jpg', 2),
    ('Black', 'https://m.media-amazon.com/images/I/41HLaX4vwiL._AC_SL1500_.jpg', 2),
    ('Gray', 'https://m.media-amazon.com/images/I/413eZrKrgnL._AC_SL1500_.jpg', 2),
    ('Violet', 'https://m.media-amazon.com/images/I/41otBVAxZzL._AC_SL1500_.jpg', 2);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/81lek2iav1L._SX522_.jpg', 1, 2),
    ('https://m.media-amazon.com/images/I/81lek2iav1L._SX522_.jpg', 1, 2),
    ('https://m.media-amazon.com/images/I/81lek2iav1L._SX522_.jpg', 1, 2),
    ('https://m.media-amazon.com/images/I/71tMPWYWRqL._SX522_.jpg', 1, 2);

-- Product 3: OnePlus 12
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('OnePlus 12', 'Flagship OnePlus phone with Hasselblad camera.', 999.99, 60, 5, 12.00, 3, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Flowy Emerald', 'https://m.media-amazon.com/images/I/41OpAzcvdLL._AC_SL1500_.jpg', 3),
    ('Glacial White', 'https://m.media-amazon.com/images/I/41J4+TiUz6L._AC_SL1500_.jpg', 3),
    ('Silky Black', 'https://m.media-amazon.com/images/I/41pR0qlI0yL._AC_SL1500_.jpg', 3);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/717Qo4MH97L._SX425_.jpg', 1, 3),
    ('https://m.media-amazon.com/images/I/61CEiTA5WWL._SX425_.jpg', 1, 3),
    ('https://m.media-amazon.com/images/I/61AplC-qoML._SX425_.jpg', 1, 3),
    ('https://m.media-amazon.com/images/I/61fVA2RaThL._SX425_.jpg', 1, 3);

-- Product 4: iQOO Neo9 Pro
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('iQOO Neo9 Pro', 'Experience the best of Google with Pixel 8 Pro.', 1099.99, 80, 10, 20.00, 4, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Conqueror Black', 'https://m.media-amazon.com/images/I/41Pt-NIpv0L._SS36_.jpg', 4),
    ('Fiery Red', 'https://m.media-amazon.com/images/I/41m-C1HHkIL._SS36_.jpg', 4);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/718jcIFYaAL._SX522_.jpg', 1, 4),
    ('https://m.media-amazon.com/images/I/71Qvj-ww7IL._SX522_.jpg', 1, 4),
    ('https://m.media-amazon.com/images/I/71jyObbkvpL._SX522_.jpg', 1, 4),
    ('https://m.media-amazon.com/images/I/719nIYVptLL._SX522_.jpg', 1, 4);

-- Product 5: Xiaomi 14
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Xiaomi 14', 'High-end Xiaomi phone with Leica optics.', 799.99, 90, 15, 25.00, 5, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Black', 'https://m.media-amazon.com/images/I/41r6mLsKMbL._SS36_.jpg', 5),
    ('Green', 'https://m.media-amazon.com/images/I/41O+9LbIOUL._SS36_.jpg', 5),
    ('White', 'https://m.media-amazon.com/images/I/41gI9yYlgQL._SS36_.jpg', 5);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/711SMo2bL4L._SX522_.jpg', 1, 5),
    ('https://m.media-amazon.com/images/I/810iQTnR09L._SX522_.jpg', 1, 5),
    ('https://m.media-amazon.com/images/I/71u6+axGiAL._SX522_.jpg', 1, 5),
    ('https://m.media-amazon.com/images/I/71-DQ2MB1cL._SX522_.jpg', 1, 5);

-- Product 6: iPhone 15
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('iPhone 15', 'The latest iPhone with stunning display.', 999.99, 40, 5, 10.00, 1, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Black', 'https://m.media-amazon.com/images/I/11lmjtsHP6L._SS36_.jpg', 6),
    ('Blue', 'https://m.media-amazon.com/images/I/11hzJkyD92L._SS36_.jpg', 6),
    ('Pink', 'https://m.media-amazon.com/images/I/11H1kzrvDwL._SS36_.jpg', 6),
    ('Green', 'https://m.media-amazon.com/images/I/11rVHB4qerL._SS36_.jpg', 6),
    ('Yellow', 'https://m.media-amazon.com/images/I/11E3lDShKBL._SS36_.jpg', 6);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/61f4dTush1L._SX522_.jpg', 1, 6),
    ('https://m.media-amazon.com/images/I/71REplb5oZL._SX522_.jpg', 1, 6),
    ('https://m.media-amazon.com/images/I/712CBkmhLhL._SX522_.jpg', 1, 6),
    ('https://m.media-amazon.com/images/I/61Rws8eHMpL._SX522_.jpg', 1, 6);

-- Product 7: Samsung Galaxy Z Fold 5
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Samsung Galaxy Z Fold 5', 'Revolutionary foldable design with large screen.', 1799.99, 65, 8, 18.00, 2, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Beige', 'https://m.media-amazon.com/images/I/41UY--r82AL._SS36_.jpg', 7),
    ('Graygreen', 'https://m.media-amazon.com/images/I/418KmWHA2oL._SS36_.jpg', 7),
    ('Phantom Black', 'https://m.media-amazon.com/images/I/41xi2LXCRPL._SS36_.jpg', 7),
    ('Green', 'https://m.media-amazon.com/images/I/418KmWHA2oL._SS36_.jpg', 7);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71Vd1+ZnY-L._SX522_.jpg', 1, 7),
    ('https://m.media-amazon.com/images/I/717Ra3t+lsL._SX522_.jpg', 1, 7),
    ('https://m.media-amazon.com/images/I/61+s+yrWo-L._SX522_.jpg', 1, 7),
    ('https://m.media-amazon.com/images/I/717Ra3t+lsL._SX522_.jpg', 1, 7);

-- Product 8: OnePlus Nord CE4
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('OnePlus Nord CE4', 'Mid-range OnePlus phone with high-end features.', 499.99, 100, 10, 20.00, 3, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Mega Blue', 'https://m.media-amazon.com/images/I/41GVgsI2ymL._SS36_.jpg', 8),
    ('Super Silver', 'https://m.media-amazon.com/images/I/31hcJbP0BuL._SS36_.jpg', 8),
    ('Ultra Orange', 'https://m.media-amazon.com/images/I/41dUwBS3TdL._SS36_.jpg', 8);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/51KJE22BKrL._SX522_.jpg', 1, 8),
    ('https://m.media-amazon.com/images/I/71QGsBvLd7L._SX522_.jpg', 1, 8),
    ('https://m.media-amazon.com/images/I/51Ot-iGKWqL._SX522_.jpg', 1, 8),
    ('https://m.media-amazon.com/images/I/518tFBwolJL._SX522_.jpg', 1, 8);

-- Product 9: realme GT 6T
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('realme GT 6T', 'Affordable Google phone with premium features.', 499.99, 75, 12, 22.00, 4, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Fluid Silver', 'https://m.media-amazon.com/images/I/41SHaUuaNqL._SS36_.jpg', 9),
    ('Miracle Purple', 'https://m.media-amazon.com/images/I/41hXt16w8pL._SS36_.jpg', 9),
    ('Razor Green', 'https://m.media-amazon.com/images/I/41VFvti1aEL._SS36_.jpg', 9);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71fhaUR7VGL._SX522_.jpg', 1, 9),
    ('https://m.media-amazon.com/images/I/81KSK+8ZbcL._SX522_.jpg', 1, 9),
    ('https://m.media-amazon.com/images/I/71mH3NJGP5L._SX522_.jpg', 1, 9),
    ('https://m.media-amazon.com/images/I/513DvnOKeoL._SX522_.jpg', 1, 9);

-- Product 10: Xiaomi Redmi Note 13
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Xiaomi Redmi Note 13', 'Budget-friendly phone with impressive performance.', 299.99, 120, 20, 30.00, 5, 1, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Arctic White', 'https://m.media-amazon.com/images/I/41ZlnmlOM-L._SS36_.jpg', 10),
    ('Chromatic Purple', 'https://m.media-amazon.com/images/I/41gF1pn3wPL._SS36_.jpg', 10),
    ('Prism Gold', 'https://m.media-amazon.com/images/I/41Hgqko42VL._SS36_.jpg', 10),
    ('Stealth Black', 'https://m.media-amazon.com/images/I/410921AT8WL._SS36_.jpg', 10);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/710bX+jrz-L._SX522_.jpg', 1, 10),
    ('https://m.media-amazon.com/images/I/81cRFYQ+yUL._SX522_.jpg', 1, 10),
    ('https://m.media-amazon.com/images/I/71C64RSsD9L._SX522_.jpg', 1, 10),
    ('https://m.media-amazon.com/images/I/71P39ApmPSL._SX522_.jpg', 1, 10);


-- 2.Laptops
-- Product 1: MacBook Pro 16-inch (Product ID: 11)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('MacBook Pro 16-inch', 'Apple MacBook Pro with M2 chip, 16-inch display.', 2499.99, 30, 5, 12.00, 1, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Silver', 'https://m.media-amazon.com/images/I/01BS+2fToYL._SS36_.jpg', 11),
    ('Space Black', 'https://m.media-amazon.com/images/I/01WVMdhLH4L._SS36_.jpg', 11);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/61RcOfisl7L._SX522_.jpg', 2, 11),
    ('https://m.media-amazon.com/images/I/713y8XK0vCL._SX522_.jpg', 2, 11),
    ('https://m.media-amazon.com/images/I/71dCB3j6jFL._SX522_.jpg', 2, 11),
    ('https://m.media-amazon.com/images/I/61ZbTYMThyL._SX522_.jpg', 2, 11);

-- Product 2: Dell XPS 15 (Product ID: 12)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Dell XPS 15', 'High-performance laptop with stunning InfinityEdge display.', 1899.99, 40, 6, 15.00, 2, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Platinum Silver', 'https://m.media-amazon.com/images/I/41iiFgYZkZL._SS36_.jpg', 12),
    ('Grey', 'https://m.media-amazon.com/images/I/31uYfs9jPML._SS36_.jpg', 12),
    ('Carbon Black', 'https://m.media-amazon.com/images/I/41iiFgYZkZL._SS36_.jpg', 12);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/51rEPuvNYFL._SX425_.jpg', 2, 12),
    ('https://m.media-amazon.com/images/I/61sqDdp8DiL._SX425_.jpg', 2, 12),
    ('https://m.media-amazon.com/images/I/61w5+uSFhUL._SX425_.jpg', 2, 12),
    ('https://m.media-amazon.com/images/I/61B4L0XtLmL._SX425_.jpg', 2, 12);

-- Product 3: HP Spectre x360 (Product ID: 13)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('HP Spectre x360', 'Premium 2-in-1 convertible laptop with a sleek design.', 1599.99, 35, 7, 18.00, 3, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Nightfall Black', 'https://m.media-amazon.com/images/I/41TYhvR5rnL._SS36_.jpg', 13),
    ('Slate Blue', 'https://m.media-amazon.com/images/I/41bHtrlvkSL._SS36_.jpg', 13);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71Sq9WQoZ4L._SY355_.jpg', 2, 13),
    ('https://m.media-amazon.com/images/I/813ChcCGfVL._SY355_.jpg', 2, 13),
    ('https://m.media-amazon.com/images/I/814QFdmlYkL._SY355_.jpg', 2, 13),
    ('https://m.media-amazon.com/images/I/81anNggBh3L._SY355_.jpg', 2, 13);

-- Product 4: Lenovo ThinkPad X1 Carbon (Product ID: 14)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Lenovo ThinkPad X1 Carbon', 'Business laptop with a sleek design and powerful performance.', 1799.99, 25, 5, 15.00, 4, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Silver', 'https://m.media-amazon.com/images/I/41YmI+NIzHL._SS36_.jpg', 14),
    ('Arctic Grey', 'https://m.media-amazon.com/images/I/41vbEaLGzAL._SS36_.jpg', 14),
    ('White', 'https://m.media-amazon.com/images/I/41vbEaLGzAL._SS36_.jpg', 14);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/81iiHfKu-iL._SX355_.jpg', 2, 14),
    ('https://m.media-amazon.com/images/I/51R3TKLjTwL._SY355_.jpg', 2, 14),
    ('https://m.media-amazon.com/images/I/61o5qu9M3mL._SY355_.jpg', 2, 14),
    ('https://m.media-amazon.com/images/I/61cNDl0PttL._SY355_.jpg', 2, 14);

-- Product 5: ASUS TUF Gaming A15 (Product ID: 15)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('ASUS TUF Gaming A15', 'High-performance gaming laptop with AMD Ryzen processor.', 1499.99, 30, 4, 20.00, 5, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Graphite Black', 'https://m.media-amazon.com/images/I/41Aw8+krn+L._SS36_.jpg', 15),
    ('Mecha Gray', 'https://m.media-amazon.com/images/I/41hw7I31VjL._SS36_.jpg', 15),
    ('Silver', 'https://m.media-amazon.com/images/I/41N2QTCprdL._SS36_.jpg', 15);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/61oby1pTjrL._SX425_.jpg', 2, 15),
    ('https://m.media-amazon.com/images/I/81x9vxrPFwS._SX425_.jpg', 2, 15),
    ('https://m.media-amazon.com/images/I/61hZjjFLEoL._SX425_.jpg', 2, 15),
    ('https://m.media-amazon.com/images/I/61fKCsXpfWL._SX425_.jpg', 2, 15);

-- Product 6: Microsoft Surface Laptop 4 (Product ID: 16)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Microsoft Surface Laptop 4', 'Ultra-thin and stylish laptop with touchscreen display.', 1399.99, 50, 6, 18.00, 6, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('White', 'https://m.media-amazon.com/images/I/81TAF+5hhfL._AC_UY327_FMwebp_QL65_.jpg', 16),
    ('Matte Black', 'https://m.media-amazon.com/images/I/717wZ9Wh4eL._AC_UY327_FMwebp_QL65_.jpg', 16),
    ('Silver', 'https://m.media-amazon.com/images/I/61wqtqb2hzL._AC_UY327_FMwebp_QL65_.jpg', 16);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/717wZ9Wh4eL._SY355_.jpg', 2, 16),
    ('https://m.media-amazon.com/images/I/51i9uO9cpyL._SY355_.jpg', 2, 16),
    ('https://m.media-amazon.com/images/I/61qfp720RCL._SY355_.jpg', 2, 16),
    ('https://m.media-amazon.com/images/I/51s8zS0Nm7L._SY355_.jpg', 2, 16);

-- Product 7: Acer Swift 5 (Product ID: 17)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Acer Swift 5', 'Lightweight laptop with long battery life and touchscreen.', 1299.99, 45, 8, 15.00, 7, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Prodigy Pink', 'https://m.media-amazon.com/images/I/51SeJhDc+8L._SS36_.jpg', 17),
    ('Pure Silver', 'https://m.media-amazon.com/images/I/41DMcVbWHVL._SS36_.jpg', 17),
    ('Silver', 'https://m.media-amazon.com/images/I/41WI1aFHmcL._SS36_.jpg', 17);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/81QboUhMoNL._SY450_.jpg', 2, 17),
    ('https://m.media-amazon.com/images/I/61IiMKU8X0L._SY450_.jpg', 2, 17),
    ('https://m.media-amazon.com/images/I/81vj2Q16AaL._SY450_.jpg', 2, 17),
    ('https://m.media-amazon.com/images/I/81FRXVi9TEL._SY450_.jpg', 2, 17);

-- Product 8: Razer Blade 15 (Product ID: 18)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Razer Blade 15', 'High-end gaming laptop with RTX graphics.', 2399.99, 20, 5, 25.00, 8, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Mercury White', 'https://th.bing.com/th?id=OPAC.FHW7FXuHRzmefQ474C474&w=168&h=150&c=17&dpr=1.3&pid=21.1', 18),
    ('Matte Black', 'https://th.bing.com/th?id=OPAC.lblr3nZ6eXo0Tg474C474&w=192&h=150&c=17&dpr=1.3&pid=21.1', 18),
    ('Silver', 'https://th.bing.com/th?id=OPAC.WjW70olfg74IPA474C474&w=177&h=150&c=17&dpr=1.3&pid=21.1', 18);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://p2-ofp.static.pub/fes/cms/2022/09/26/i6zlcap44kafmcywlh54d9rd1wieh1215035.png', 2, 18),
    ('https://p3-ofp.static.pub/fes/cms/2022/09/26/hif2cnxhohr2dh3dwgg1trg70pip4y581012.png', 2, 18),
    ('https://p2-ofp.static.pub/fes/cms/2022/09/26/ukkpvjzdiiwwj0l4p0zs6s3938sxsm336408.png', 2, 18),
    ('https://p2-ofp.static.pub/fes/cms/2022/09/26/6vqcmbpcfs5fddq9vxtf7mlo3064tj549746.png', 2, 18);

-- Product 9: HP Envy 13 (Product ID: 19)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('HP Envy 13', 'Compact and powerful laptop for everyday use.', 1099.99, 40, 7, 10.00, 3, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Black', 'https://m.media-amazon.com/images/I/71EQNTOC6gL._AC_UY327_FMwebp_QL65_.jpg', 19),
    ('White', 'https://m.media-amazon.com/images/I/71hAxMqfRuL._AC_UY327_FMwebp_QL65_.jpg', 19),
    ('Rose Gold', 'https://m.media-amazon.com/images/I/61uIVLaX0KL._AC_UY327_FMwebp_QL65_.jpg', 19);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71hAxMqfRuL._SY355_.jpg', 2, 19),
    ('https://m.media-amazon.com/images/I/81XWman-e6L._SY355_.jpg', 2, 19),
    ('https://m.media-amazon.com/images/I/81T0Ff4Yx4L._SY355_.jpg', 2, 19),
    ('https://m.media-amazon.com/images/I/81W8o-lB3jL._SY355_.jpg', 2, 19);

-- Product 10: ASUS ZenBook Flip 14 (Product ID: 20)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('ASUS ZenBook Flip 14', 'Convertible laptop with a 360-degree hinge and touchscreen display.', 1199.99, 35, 6, 18.00, 5, 2, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('White', 'https://m.media-amazon.com/images/I/81UScz47obL._AC_UY327_FMwebp_QL65_.jpg', 20),
    ('Icicle Silver', 'https://m.media-amazon.com/images/I/71OTEFdMT3L._AC_UY327_FMwebp_QL65_.jpg', 20);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71OTEFdMT3L._SY450_.jpg', 2, 20),
    ('https://m.media-amazon.com/images/I/71emy+jDA4L._SY450_.jpg', 2, 20),
    ('https://m.media-amazon.com/images/I/61LDn3ifKSL._SY450_.jpg', 2, 20),
    ('https://m.media-amazon.com/images/I/81XjsdpbhxL._SY450_.jpg', 2, 20);


-- 3.tablets
-- Product 1: iPad Pro 12.9-inch (Product ID: 21)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('iPad Pro 12.9-inch', 'Apple iPad Pro with M2 chip, 12.9-inch Liquid Retina XDR display.', 1099.99, 60, 5, 10.00, 1, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Blue', 'https://m.media-amazon.com/images/I/11s-WE1J8yL._SS36_.jpg', 21),
    ('Silver', 'https://m.media-amazon.com/images/I/01DLNekvtdL._SS36_.jpg', 21),
    ('Yellow', 'https://m.media-amazon.com/images/I/11v0M5o+nGL._SS36_.jpg', 21),
    ('Pink', 'https://m.media-amazon.com/images/I/11BtlDw3XeL._SS36_.jpg', 21);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/91psKPUkS4L._SX522_.jpg', 3, 21),
    ('https://m.media-amazon.com/images/I/61oo+mrbI2L._SX522_.jpg', 3, 21),
    ('https://m.media-amazon.com/images/I/71Xe1DNqYgL._SX522_.jpg', 3, 21),
    ('https://m.media-amazon.com/images/I/61dExJXHLLL._SX522_.jpg', 3, 21);

-- Product 2: Samsung Galaxy Tab S8 (Product ID: 22)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Samsung Galaxy Tab S8', 'Powerful Android tablet with a vivid AMOLED display.', 799.99, 50, 6, 15.00, 2, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Graphite', 'https://m.media-amazon.com/images/I/31XcpPWbP8L._SS36_.jpg', 22),
    ('Silver', 'https://m.media-amazon.com/images/I/31jJigLcjfL._SS36_.jpg', 22),
    ('Pink Gold', 'https://m.media-amazon.com/images/I/41wc26WHZYL._SS36_.jpg', 22);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/81elHEv1QrL._SX355_.jpg', 3, 22),
    ('https://m.media-amazon.com/images/I/51o3vrdfROL._SX355_.jpg', 3, 22),
    ('https://m.media-amazon.com/images/I/81gq7g7vWZL._SX355_.jpg', 3, 22),
    ('https://m.media-amazon.com/images/I/81+Ec2RkiWL._SX355_.jpg', 3, 22);

-- Product 3: Microsoft Surface Pro 9 (Product ID: 23)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Microsoft Surface Pro 9', '2-in-1 laptop with detachable keyboard and touchscreen.', 1199.99, 40, 7, 20.00, 3, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Forest', 'https://m.media-amazon.com/images/I/51zeYMcjR8L._SX355_.jpg', 23),
    ('Graphite', 'https://m.media-amazon.com/images/I/51L40XTsN+L._AC_UY327_FMwebp_QL65_.jpg', 23),
    ('Sapphire', 'https://m.media-amazon.com/images/I/51E7GuK7JzL._AC_UY327_FMwebp_QL65_.jpg', 23);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/61FQxxg3HHL._SY355_.jpg', 3, 23),
    ('https://m.media-amazon.com/images/I/71lmZ6bvyML._SY355_.jpg', 3, 23),
    ('https://m.media-amazon.com/images/I/71m1c0WjnUL._SY355_.jpg', 3, 23),
    ('https://m.media-amazon.com/images/I/71XWkAS8n4L._SY355_.jpg', 3, 23);

-- Product 4: Lenovo Tab P11 Pro (Product ID: 24)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Lenovo Tab P11 Pro', 'Lenovo tablet with an OLED display and Dolby Atmos sound.', 499.99, 70, 8, 15.00, 4, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Black', 'https://m.media-amazon.com/images/I/517Rd6DfZRL._AC_UY327_FMwebp_QL65_.jpg', 24),
    ('White', 'https://m.media-amazon.com/images/I/51fWpRMV-dL._AC_UY327_FMwebp_QL65_.jpg', 24);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/517Rd6DfZRL._SY355_.jpg', 3, 24),
    ('https://m.media-amazon.com/images/I/71Jl-rpViyL._SY355_.jpg', 3, 24),
    ('https://m.media-amazon.com/images/I/71hHDG6WJpL._SY355_.jpg', 3, 24),
    ('https://m.media-amazon.com/images/I/619oIpamqdL._SY355_.jpg', 3, 24);

-- Product 5: Huawei MatePad Pro (Product ID: 25)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Huawei MatePad Pro', 'Huawei tablet with a FullView display and Harmon Kardon speakers.', 699.99, 60, 9, 18.00, 5, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Silver', 'https://th.bing.com/th?id=OIP.nDchzQjSvYz_khieYgNZ8wHaCe&w=350&h=116&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2', 25);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/plp-x/tablet/autumn-2024-wearable-launch/kv/huawei-matepad-pro-12-2-kv.jpg', 3, 25),
    ('https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/plp-x/tablet/autumn-2024-wearable-launch/matepad-pro-series/huawei-matepad-pro-12-2-card-2.jpg', 3, 25),
    ('https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/plp-x/tablet/autumn-2024-wearable-launch/matepad-pro-series/huawei-matepad-pro-12-2-card-3.jpg', 3, 25);

-- Product 6: Amazon Fire HD 10 (Product ID: 26)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Amazon Fire HD 10', 'Affordable tablet with a vibrant 1080p display.', 149.99, 100, 10, 5.00, 6, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Black', 'https://m.media-amazon.com/images/I/61KVpz9L0gL._SX679_.jpg', 26);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/61KVpz9L0gL._SX679_.jpg', 3, 26),
    ('https://m.media-amazon.com/images/I/51iABjSt3BS._SX425_.jpg', 3, 26),
    ('https://m.media-amazon.com/images/I/51yM7x129-S._SX425_.jpg', 3, 26),
    ('https://m.media-amazon.com/images/I/61BOCaFT3LS._SX425_.jpg', 3, 26);

-- Product 7: Xiaomi Pad 5 (Product ID: 27)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Xiaomi Pad 5', 'Xiaomi tablet with a high-refresh-rate screen.', 399.99, 80, 12, 10.00, 7, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Graphite Gray', 'https://m.media-amazon.com/images/I/41btIYZ41zL._SS36_.jpg', 27),
    ('Mist Blue', 'https://m.media-amazon.com/images/I/41t+pergB2L._SS36_.jpg', 27),
    ('Quick Silver', 'https://m.media-amazon.com/images/I/41ejwwPkddL._SS36_.jpg', 27);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71VPj4c-OZL._SY355_.jpg', 3, 27),
    ('https://m.media-amazon.com/images/I/41Uok6wh6IL._SY355_.jpg', 3, 27),
    ('https://m.media-amazon.com/images/I/51UOSi-IEDL._SY355_.jpg', 3, 27),
    ('https://m.media-amazon.com/images/I/81Xhy0FznvL._SY355_.jpg', 3, 27);

-- Product 8: Lenovo Yoga Tab 13 (Product ID: 28)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Lenovo Yoga Tab 13', 'Entertainment tablet with built-in kickstand and Dolby Atmos.', 599.99, 55, 8, 12.00, 4, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Shadow Black', 'https://m.media-amazon.com/images/I/71BY+Xg9OuL._AC_UY327_FMwebp_QL65_.jpg', 28);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71BY+Xg9OuL._SY355_.jpg', 3, 28),
    ('https://m.media-amazon.com/images/I/617ZXE-782L._SY355_.jpg', 3, 28),
    ('https://m.media-amazon.com/images/I/61ZoWES+l6L._SY355_.jpg', 3, 28),
    ('https://m.media-amazon.com/images/I/61BmLjte2HL._SY355_.jpg', 3, 28);

-- Product 9: Galaxy Tab A8 (Product ID: 29)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Galaxy Tab A8', 'Samsung Galaxy Tab with 10.5-inch screen.', 229.99, 90, 15, 20.00, 2, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Gray', 'https://m.media-amazon.com/images/I/418jsYxG-nL._SS36_.jpg', 29),
    ('Silver', 'https://m.media-amazon.com/images/I/41SOyqWKhOL._SS36_.jpg', 29),
    ('Pink Gold', 'https://m.media-amazon.com/images/I/41nB6GnNb+L._SS36_.jpg', 29);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/91oRqY6-q-L._SX355_.jpg', 3, 29),
    ('https://m.media-amazon.com/images/I/81NIGIiODrL._SX355_.jpg', 3, 29),
    ('https://m.media-amazon.com/images/I/81K3N2WZlaL._SX355_.jpg', 3, 29),
    ('https://m.media-amazon.com/images/I/41UBXRxL4GL._SX355_.jpg', 3, 29);

-- Product 10: iPad Air (Product ID: 30)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('iPad Air', 'Apple iPad Air with 10.9-inch Liquid Retina display.', 599.99, 75, 6, 10.00, 1, 3, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Space Gray', 'https://m.media-amazon.com/images/I/31vRh393vJL._SS36_.jpg', 30),
    ('Sky Blue', 'https://m.media-amazon.com/images/I/415dGcORn2L._SS36_.jpg', 30),
    ('Starlight', 'https://m.media-amazon.com/images/I/31GVzjFSx6L._SS36_.jpg', 30),
    ('Pink', 'https://m.media-amazon.com/images/I/31dtPGkNhcL._SS36_.jpg', 30);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71IeLz1YLZL._SX522_.jpg', 3, 30),
    ('https://m.media-amazon.com/images/I/71SQXY9KjHL._SX522_.jpg', 3, 30),
    ('https://m.media-amazon.com/images/I/81X+ITPnFXL._SX522_.jpg', 3, 30),
    ('https://m.media-amazon.com/images/I/61WGQT0l-fL._SX522_.jpg', 3, 30);

-- 4.pods


-- Product 1: Apple AirPods Pro (2nd Generation)
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Apple AirPods Pro (2nd Generation)', 'Apple AirPods with noise cancellation and spatial audio.', 249.99, 100, 10, 15.00, 1, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Blue', 'https://m.media-amazon.com/images/I/01++SjnuXRL._SS36_.jpg', 31),
    ('Midnight', 'https://m.media-amazon.com/images/I/010IQoKTBtL._SS36_.jpg', 31),
    ('Orange', 'https://m.media-amazon.com/images/I/01TmK-MR2HL._SS36_.jpg', 31),
    ('Purple', 'https://m.media-amazon.com/images/I/01VYWMC3JfL._SS36_.jpg', 31);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71QFjliR-ML._SX425_.jpg', 4, 31),
    ('https://m.media-amazon.com/images/I/71R62N5phFL._SX425_.jpg', 4, 31),
    ('https://m.media-amazon.com/images/I/713vj053ZTL._SX425_.jpg', 4, 31),
    ('https://m.media-amazon.com/images/I/71UiwdNmlEL._SX425_.jpg', 4, 31);
    
-- Product 2: Sony WF-1000XM4
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Sony WF-1000XM4', 'Sony wireless earbuds with industry-leading noise cancellation.', 279.99, 80, 8, 20.00, 2, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Black', 'https://m.media-amazon.com/images/I/31OY5Kup6aL._SS36_.jpg', 32),
    ('Silver', 'https://m.media-amazon.com/images/I/21laUDEG4BL._SS36_.jpg', 32);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/61MgPeUAfvL._SY355_.jpg', 4, 32),
    ('https://m.media-amazon.com/images/I/913a-1k0tfL._SY355_.jpg', 4, 32),
    ('https://m.media-amazon.com/images/I/91rBRERHmoL._SY355_.jpg', 4, 32),
    ('https://m.media-amazon.com/images/I/913a-1k0tfL._SY355_.jpg', 4, 32);

-- Product 3: Samsung Galaxy Buds2 Pro
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Samsung Galaxy Buds2 Pro', 'Samsung wireless earbuds with high-fidelity sound.', 229.99, 90, 12, 18.00, 3, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Black', 'https://m.media-amazon.com/images/I/21Y3xVnL0BL._SS36_.jpg', 33),
    ('White', 'https://m.media-amazon.com/images/I/21Fvx4rU69L._SS36_.jpg', 33),
    ('Bora Purple', 'https://m.media-amazon.com/images/I/31Tm22hUx1L._SS36_.jpg', 33);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/61Qqg+T8nsL._SX355_.jpg', 4, 33),
    ('https://m.media-amazon.com/images/I/71YCCEPBguL._SY355_.jpg', 4, 33),
    ('https://m.media-amazon.com/images/I/61lEskbCaoL._SY355_.jpg', 4, 33),
    ('https://m.media-amazon.com/images/I/51wp23mi8qL._SY355_.jpg', 4, 33);

-- Product 4: Bose QuietComfort Earbuds II
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Bose QuietComfort Earbuds II', 'Bose wireless earbuds with noise cancellation and superior sound.', 299.99, 70, 10, 15.00, 4, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Triple Black', 'https://m.media-amazon.com/images/I/31K+mnegQVL._SS36_.jpg', 34),
    ('Midnight Blue', 'https://m.media-amazon.com/images/I/31zgZaRijQL._SS36_.jpg', 34),
    ('Moonstone Blue', 'https://m.media-amazon.com/images/I/31vw4ZrItxL._SS36_.jpg', 34),
    ('Smoke White', 'https://m.media-amazon.com/images/I/31hUu+8D5gL._SS36_.jpg', 34),
    ('Soapstone', 'https://m.media-amazon.com/images/I/31XBsUEBGOL._SS36_.jpg', 34);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/51DOzlkiBTL._SY355_.jpg', 4, 34),
    ('https://m.media-amazon.com/images/I/713xRXpc+JL._SY355_.jpg', 4, 34),
    ('https://m.media-amazon.com/images/I/51NxZjYgqyL._SY355_.jpg', 4, 34),
    ('https://m.media-amazon.com/images/I/51Bvor+ZF0L._SY355_.jpg', 4, 34);

-- Product 5: Jabra Elite 7 Pro
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Jabra Elite 7 Pro', 'Jabra wireless earbuds with adjustable noise cancellation.', 199.99, 85, 10, 18.00, 5, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Titanium Black', 'https://m.media-amazon.com/images/I/31J83BkgcqL._SS36_.jpg', 35),
    ('Black', 'https://m.media-amazon.com/images/I/31e6bcnQuGL._SS36_.jpg', 35);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/51C4tMQrgJL._SY355_.jpg', 4, 35),
    ('https://m.media-amazon.com/images/I/61ILPNiXoQL._SY355_.jpg', 4, 35),
    ('https://m.media-amazon.com/images/I/51+1t88AlXL._SY355_.jpg', 4, 35),
    ('https://m.media-amazon.com/images/I/71H5sQF6oFL._SY355_.jpg', 4, 35);

-- Product 6: Sennheiser Momentum True Wireless 3
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Sennheiser Momentum True Wireless 3', 'Sennheiser earbuds with adaptive noise cancellation and superior sound.', 249.99, 60, 10, 15.00, 6, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Black Copper', 'https://m.media-amazon.com/images/I/41GCOdrFNjL._SS36_.jpg', 36),
    ('White Silver', 'https://m.media-amazon.com/images/I/31fh7fw7lcL._SS36_.jpg', 36),
    ('Black Graphite', 'https://m.media-amazon.com/images/I/41hEEy89bWL._SS36_.jpg', 36);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/71qPN6X0feL._SY355_.jpg', 4, 36),
    ('https://m.media-amazon.com/images/I/71BJ93zC2TL._SY355_.jpg', 4, 36),
    ('https://m.media-amazon.com/images/I/71yEZaWwamL._SY355_.jpg', 4, 36),
    ('https://m.media-amazon.com/images/I/61K7GxMW5aL._SY355_.jpg', 4, 36);

-- Product 7: Beats Fit Pro
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Beats Fit Pro', 'Beats wireless earbuds with active noise cancellation and spatial audio.', 199.99, 75, 8, 15.00, 7, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Red', 'https://m.media-amazon.com/images/I/31ftgeUZJNL._SS36_.jpg', 37),
    ('White', 'https://m.media-amazon.com/images/I/21QaVt+seOL._SS36_.jpg', 37),
    ('Black', 'https://m.media-amazon.com/images/I/31FSLCZWqVL._SS36_.jpg', 37);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/51Wp5EgnA-L._SX522_.jpg', 4, 37),
    ('https://m.media-amazon.com/images/I/51YGybZecSL._SX522_.jpg', 4, 37),
    ('https://m.media-amazon.com/images/I/51x7B+f9uZL._SX522_.jpg', 4, 37),
    ('https://m.media-amazon.com/images/I/61nCL5CfKPL._SX522_.jpg', 4, 37);

-- Product 8: Anker Soundcore Liberty Air 2 Pro
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Anker Soundcore Liberty Air 2 Pro', 'Anker wireless earbuds with personalized noise cancellation.', 129.99, 100, 12, 10.00, 8, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Onyx Black', 'https://m.media-amazon.com/images/I/31J-366AczL._SS36_.jpg', 38),
    ('Blue', 'https://m.media-amazon.com/images/I/31ohEYgId1L._SS36_.jpg', 38),
    ('Light Blue', 'https://m.media-amazon.com/images/I/31Id9281Y1L._SS36_.jpg', 38),
    ('White', 'https://m.media-amazon.com/images/I/31dZHeGLdjL._SS36_.jpg', 38),
    ('Rose Pink', 'https://m.media-amazon.com/images/I/31AsYj-m9EL._SS36_.jpg', 38);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/51snKP8HWiL._SY355_.jpg', 4, 38),
    ('https://m.media-amazon.com/images/I/612Oz4B-HPL._SY355_.jpg', 4, 38),
    ('https://m.media-amazon.com/images/I/61aX8Anaj4L._SY355_.jpg', 4, 38),
    ('https://m.media-amazon.com/images/I/61wn-GmqbSL._SY355_.jpg', 4, 38);

-- Product 9: Google Pixel Buds Pro
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('Google Pixel Buds Pro', 'Google wireless earbuds with active noise cancellation and hands-free help.', 199.99, 90, 10, 18.00, 9, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Bay', 'https://m.media-amazon.com/images/I/31V53G1pXaL._SS36_.jpg', 39),
    ('Lemongrass', 'https://m.media-amazon.com/images/I/31vVrzuLRTL._SS36_.jpg', 39),
    ('Porcelain', 'https://m.media-amazon.com/images/I/31425F9zJxL._SS36_.jpg', 39);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/61qVCvt2xjL._SY355_.jpg', 4, 39),
    ('https://m.media-amazon.com/images/I/81cgf7TwfqL._SY355_.jpg', 4, 39),
    ('https://m.media-amazon.com/images/I/81dOy0p6dWL._SY355_.jpg', 4, 39),
    ('https://m.media-amazon.com/images/I/71yc1DogoOL._SY355_.jpg', 4, 39);

-- Product 10: JBL Live Pro 2
INSERT INTO product (product_name, description, price, stock_quantity, threshold, max_discount, brand_id, category_id, seller_id) 
VALUES ('JBL Live Pro 2', 'JBL wireless earbuds with adaptive noise cancellation and up to 40 hours of battery life.', 149.99, 85, 10, 15.00, 10, 4, 1);
INSERT INTO color (color_name, color_url, product_id) 
VALUES 
    ('Silver', 'https://m.media-amazon.com/images/I/31hrq1vpR0L._SS36_.jpg', 40),
    ('Blue', 'https://m.media-amazon.com/images/I/31a+oslYqhL._SS36_.jpg', 40),
    ('Pink', 'https://m.media-amazon.com/images/I/31B-pWtWZgL._SS36_.jpg', 40);
INSERT INTO product_image (image_url, category_id, product_id) 
VALUES 
    ('https://m.media-amazon.com/images/I/61lZnF+HMcL._SY355_.jpg', 4, 40),
    ('https://m.media-amazon.com/images/I/51OpA0TV9iL._SY355_.jpg', 4, 40),
    ('https://m.media-amazon.com/images/I/51nO3comHML._SY355_.jpg', 4, 40);





select * from product;
select * from brand;
select * from color;
select * from product_image;
-- 1


drop database RevshopP1ProjectDevelopementDatabase;