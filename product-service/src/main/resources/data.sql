DELETE FROM products;


-- Electronics - Smartphones
INSERT INTO products (sku, name, description, price, stock_quantity, brand, category, sub_category, image_url, weight, dimensions, color, material, is_active) VALUES
('SP-IPH-13P', 'iPhone 13 Pro', '5G smartphone with A15 Bionic chip, Pro camera system', 999.99, 50, 'Apple', 'Electronics', 'Smartphones', 'https://assets.example.com/iphone-13-pro.jpg', 0.204, '146.7 x 71.5 x 7.65 mm', 'Sierra Blue', 'Glass and Stainless Steel', true),
('SP-SAM-S21', 'Samsung Galaxy S21', '5G smartphone with 108MP camera, 6.8" display', 899.99, 45, 'Samsung', 'Electronics', 'Smartphones', 'https://assets.example.com/galaxy-s21.jpg', 0.227, '165.1 x 75.6 x 8.9 mm', 'Phantom Black', 'Glass and Aluminum', true),
('SP-GGL-PX6', 'Google Pixel 6', '5G smartphone with Google Tensor chip', 699.99, 30, 'Google', 'Electronics', 'Smartphones', 'https://assets.example.com/pixel-6.jpg', 0.207, '158.6 x 74.8 x 8.9 mm', 'Stormy Black', 'Glass and Aluminum', true),

-- Electronics - Laptops
('LP-MAC-14P', 'MacBook Pro 14"', 'M1 Pro chip, 16GB RAM, 512GB SSD', 1999.99, 25, 'Apple', 'Electronics', 'Laptops', 'https://assets.example.com/macbook-pro-14.jpg', 1.6, '312.6 x 221.2 x 15.5 mm', 'Space Gray', 'Aluminum', true),
('LP-DEL-XPS', 'Dell XPS 15', 'Intel i9, 32GB RAM, 1TB SSD, RTX 3050 Ti', 2199.99, 20, 'Dell', 'Electronics', 'Laptops', 'https://assets.example.com/dell-xps-15.jpg', 1.81, '344.7 x 230.1 x 18 mm', 'Platinum Silver', 'Aluminum', true),
('LP-LEN-X1', 'ThinkPad X1 Carbon', 'Intel i7, 16GB RAM, 512GB SSD', 1799.99, 30, 'Lenovo', 'Electronics', 'Laptops', 'https://assets.example.com/thinkpad-x1.jpg', 1.13, '314.5 x 221.6 x 14.9 mm', 'Black', 'Carbon Fiber', true),

-- Fashion - Sneakers
('SH-NIK-AF1', 'Nike Air Force 1', 'Classic white sneakers with air cushioning', 99.99, 100, 'Nike', 'Fashion', 'Footwear', 'https://assets.example.com/nike-af1.jpg', 0.45, 'US 6-13', 'White', 'Leather', true),
('SH-ADI-UB22', 'Adidas Ultraboost 22', 'Running shoes with Boost technology', 180.00, 75, 'Adidas', 'Fashion', 'Footwear', 'https://assets.example.com/ultraboost-22.jpg', 0.32, 'US 7-14', 'Core Black', 'Primeknit', true),
('SH-PUM-RS', 'Puma RS-X', 'Chunky retro-style sneakers', 110.00, 60, 'Puma', 'Fashion', 'Footwear', 'https://assets.example.com/puma-rsx.jpg', 0.38, 'US 6-12', 'White/Blue', 'Mesh and Leather', true),

-- Home & Living - Furniture
('FR-SOFA-01', 'Modern L-Shaped Sectional', 'Contemporary sectional sofa with chaise', 1299.99, 10, 'Article', 'Home', 'Furniture', 'https://assets.example.com/sectional.jpg', 85.0, '259 x 163 x 84 cm', 'Gray', 'Polyester', true),
('FR-BED-QN', 'Queen Platform Bed', 'Modern platform bed with headboard', 799.99, 15, 'Floyd', 'Home', 'Furniture', 'https://assets.example.com/platform-bed.jpg', 63.5, '157.5 x 208.3 x 86.4 cm', 'Walnut', 'Wood', true),
('FR-DESK-01', 'Standing Desk', 'Electric height-adjustable desk', 649.99, 20, 'Fully', 'Home', 'Furniture', 'https://assets.example.com/standing-desk.jpg', 47.0, '152.4 x 76.2 x 62-127 cm', 'Black', 'Steel and Bamboo', true),

-- Smart Home
('SH-AMZN-E4', 'Echo (4th Gen)', 'Smart speaker with Alexa', 99.99, 50, 'Amazon', 'Electronics', 'Smart Home', 'https://assets.example.com/echo-4.jpg', 0.97, '144 x 144 x 133 mm', 'Charcoal', 'Plastic', true),
('SH-NEST-T3', 'Nest Thermostat', 'Smart thermostat with energy saving features', 129.99, 40, 'Google', 'Electronics', 'Smart Home', 'https://assets.example.com/nest.jpg', 0.23, '84 x 84 x 27 mm', 'Snow', 'Plastic and Glass', true),
('SH-PHIL-HUE', 'Philips Hue Starter Kit', 'Smart LED bulb kit with bridge', 199.99, 35, 'Philips', 'Electronics', 'Smart Home', 'https://assets.example.com/hue-kit.jpg', 0.45, 'Various', 'White', 'Glass and Plastic', true),

-- Gaming
('GM-PS5-DE', 'PlayStation 5 Digital', 'Next-gen gaming console (Digital Edition)', 399.99, 15, 'Sony', 'Electronics', 'Gaming', 'https://assets.example.com/ps5-digital.jpg', 3.9, '390 x 260 x 104 mm', 'White', 'Plastic', true),
('GM-XBX-X', 'Xbox Series X', 'Next-gen gaming console', 499.99, 18, 'Microsoft', 'Electronics', 'Gaming', 'https://assets.example.com/xbox-series-x.jpg', 4.45, '301 x 151 x 151 mm', 'Black', 'Plastic', true),
('GM-NSW-OLED', 'Nintendo Switch OLED', 'Portable gaming console with OLED screen', 349.99, 25, 'Nintendo', 'Electronics', 'Gaming', 'https://assets.example.com/switch-oled.jpg', 0.42, '242 x 102 x 13.9 mm', 'White', 'Plastic', true);
