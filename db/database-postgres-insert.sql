DELETE FROM coupon_usage;
DELETE FROM activity_logs;
DELETE FROM customer_preferences;
DELETE FROM customer_addresses;
DELETE FROM shippings;
DELETE FROM payments;
DELETE FROM order_items;
DELETE FROM cart_items;
DELETE FROM orders;
DELETE FROM carts;
DELETE FROM products;
DELETE FROM categories;
DELETE FROM coupons;
DELETE FROM promotions;
DELETE FROM system_settings;
DELETE FROM users;

INSERT INTO users (username, password, email, full_name, role, phone_number, address, city, country, postal_code, is_active) VALUES
('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'admin@example.com', 'Quản trị viên', 'ROLE_ADMIN', '0901234567', '123 Đường ABC', 'Hồ Chí Minh', 'Vietnam', '70000', true),
('user1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user1@example.com', 'Nguyễn Văn A', 'ROLE_USER', '0902345678', '456 Đường XYZ', 'Hà Nội', 'Vietnam', '10000', true),
('user2', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user2@example.com', 'Trần Thị B', 'ROLE_USER', '0903456789', '789 Đường DEF', 'Đà Nẵng', 'Vietnam', '50000', true),
('user3', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user3@example.com', 'Lê Văn C', 'ROLE_USER', '0904567890', '321 Đường GHI', 'Hải Phòng', 'Vietnam', '18000', true),
('user4', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user4@example.com', 'Phạm Thị D', 'ROLE_USER', '0905678901', '159 Đường JKL', 'Cần Thơ', 'Vietnam', '94000', true),
('user5', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user5@example.com', 'Hoàng Văn E', 'ROLE_USER', '0906789012', '753 Đường MNO', 'Nha Trang', 'Vietnam', '65000', true),
('user6', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user6@example.com', 'Võ Thị F', 'ROLE_USER', '0907890123', '951 Đường PQR', 'Vũng Tàu', 'Vietnam', '79000', true),
('user7', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user7@example.com', 'Đặng Văn G', 'ROLE_USER', '0908901234', '357 Đường STU', 'Huế', 'Vietnam', '53000', true),
('user8', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user8@example.com', 'Bùi Thị H', 'ROLE_USER', '0909012345', '258 Đường VWX', 'Quy Nhon', 'Vietnam', '59000', true),
('user9', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user9@example.com', 'Đỗ Văn I', 'ROLE_USER', '0910123456', '147 Đường YZA', 'Phan Thiết', 'Vietnam', '80000', true),
('user10', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user10@example.com', 'Lý Thị J', 'ROLE_USER', '0911234567', '369 Đường BCD', 'Đà Lạt', 'Vietnam', '67000', true),
('user11', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user11@example.com', 'Trương Văn K', 'ROLE_USER', '0912345678', '741 Đường EFG', 'Buôn Ma Thuột', 'Vietnam', '63000', true),
('user12', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user12@example.com', 'Ngô Thị L', 'ROLE_USER', '0913456789', '852 Đường HIJ', 'Pleiku', 'Vietnam', '62000', true),
('user13', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user13@example.com', 'Lương Văn M', 'ROLE_USER', '0914567890', '963 Đường KLM', 'Long Xuyên', 'Vietnam', '90000', true),
('user14', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user14@example.com', 'Vũ Thị N', 'ROLE_USER', '0915678901', '159 Đường NOP', 'Mỹ Tho', 'Vietnam', '92000', true),
('user15', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user15@example.com', 'Phan Văn O', 'ROLE_USER', '0916789012', '753 Đường QRS', 'Rạch Giá', 'Vietnam', '95000', true),
('user16', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user16@example.com', 'Dương Thị P', 'ROLE_USER', '0917890123', '951 Đường TUV', 'Cà Mau', 'Vietnam', '97000', true),
('user17', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user17@example.com', 'Tôn Văn Q', 'ROLE_USER', '0918901234', '357 Đường WXY', 'Bạc Liêu', 'Vietnam', '96000', true),
('user18', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user18@example.com', 'Mai Thị R', 'ROLE_USER', '0919012345', '258 Đường ZAB', 'Sóc Trăng', 'Vietnam', '98000', true),
('user19', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user19@example.com', 'Hồ Văn S', 'ROLE_USER', '0920123456', '147 Đường CDE', 'Cao Lãnh', 'Vietnam', '91000', true),
('user20', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user20@example.com', 'Chu Thị T', 'ROLE_USER', '0921234567', '369 Đường FGH', 'Vĩnh Long', 'Vietnam', '93000', true),
('user21', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user21@example.com', 'Đinh Văn U', 'ROLE_USER', '0922345678', '741 Đường IJK', 'Bến Tre', 'Vietnam', '86000', true),
('user22', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user22@example.com', 'Lâm Thị V', 'ROLE_USER', '0923456789', '852 Đường LMN', 'Trà Vinh', 'Vietnam', '87000', true),
('user23', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user23@example.com', 'Tạ Văn W', 'ROLE_USER', '0924567890', '963 Đường OPQ', 'Kiên Giang', 'Vietnam', '92000', true),
('user24', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user24@example.com', 'Đoàn Thị X', 'ROLE_USER', '0925678901', '159 Đường RST', 'An Giang', 'Vietnam', '88000', true),
('user25', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user25@example.com', 'Hà Văn Y', 'ROLE_USER', '0926789012', '753 Đường UVW', 'Tây Ninh', 'Vietnam', '80000', true)
ON CONFLICT (username) DO NOTHING;

INSERT INTO categories (name, description) VALUES
('Đồ uống', 'Các loại nước giải khát, nước ép, cà phê'),
('Món ăn', 'Các món ăn chính, món phụ, món tráng miệng'),
('Bánh ngọt', 'Bánh kem, bánh mì, bánh quy'),
('Đồ ăn vặt', 'Snack, khoai tây chiên, bắp rang'),
('Thức ăn nhanh', 'Burger, pizza, gà rán'),
('Trái cây', 'Các loại trái cây tươi'),
('Rau củ', 'Rau xanh và củ quả'),
('Thịt cá', 'Thịt tươi sống và hải sản'),
('Sữa và chế phẩm', 'Sữa tươi, sữa chua, phô mai'),
('Gia vị', 'Muối, đường, tiêu, ớt'),
('Đồ khô', 'Mì gói, gạo, đậu'),
('Kẹo bánh', 'Kẹo, bánh kẹo các loại'),
('Đồ đông lạnh', 'Thực phẩm đông lạnh'),
('Nước ép', 'Nước ép trái cây tươi'),
('Trà sữa', 'Trà sữa các loại'),
('Cà phê', 'Cà phê rang xay, cà phê hòa tan'),
('Nước suối', 'Nước tinh khiết'),
('Bia rượu', 'Bia, rượu các loại'),
('Nước ngọt', 'Coca, Pepsi, Fanta'),
('Nước tăng lực', 'Red Bull, Number One'),
('Sữa đậu nành', 'Sữa đậu nành các loại'),
('Yogurt', 'Sữa chua các loại'),
('Đồ chay', 'Thực phẩm chay'),
('Đồ hộp', 'Đồ hộp các loại'),
('Mứt', 'Mứt các loại'),
('Mật ong', 'Mật ong thiên nhiên')
ON CONFLICT DO NOTHING;

INSERT INTO products (name, description, price, discount_price, stock, category_id, sku, brand, image_url, is_active, is_featured, view_count, sold_count, rating, review_count) VALUES
('Cà phê sữa đá', 'Cà phê pha với sữa đặc, đá viên', 25000.00, 22000.00, 100, 1, 'CF001', 'Highlands', 'https://example.com/cf-sua-da.jpg', true, true, 150, 45, 4.5, 30),
('Trà đào cam sả', 'Trà đào kết hợp cam tươi và sả', 30000.00, NULL, 80, 1, 'TD001', 'Gong Cha', 'https://example.com/tra-dao.jpg', true, true, 120, 35, 4.7, 25),
('Cơm tấm sườn', 'Cơm tấm với sườn nướng, bì, chả', 45000.00, 40000.00, 50, 2, 'CT001', 'Bún Mộc', 'https://example.com/com-tam.jpg', true, false, 80, 20, 4.3, 15),
('Bánh flan', 'Bánh flan mềm mịn, vị caramel', 20000.00, NULL, 60, 3, 'BF001', 'Tiệm Bánh', 'https://example.com/banh-flan.jpg', true, false, 90, 28, 4.6, 20),
('Bánh mì thịt nướng', 'Bánh mì giòn với thịt nướng thơm lừng', 35000.00, 30000.00, 40, 2, 'BM001', 'Bánh Mì Ngon', 'https://example.com/banh-mi.jpg', true, true, 200, 60, 4.8, 45),
('Coca Cola', 'Nước ngọt có gas', 15000.00, NULL, 200, 1, 'CC001', 'Coca Cola', 'https://example.com/coca.jpg', true, false, 300, 150, 4.2, 80),
('Khoai tây chiên', 'Khoai tây chiên giòn rụm', 25000.00, 20000.00, 70, 4, 'KT001', 'Lays', 'https://example.com/khoai-tay.jpg', true, false, 100, 40, 4.4, 30),
('Burger bò', 'Burger với thịt bò tươi ngon', 65000.00, 55000.00, 30, 5, 'BG001', 'McDonalds', 'https://example.com/burger.jpg', true, true, 180, 50, 4.6, 35),
('Pizza Margherita', 'Pizza Ý cổ điển với phô mai', 180000.00, 150000.00, 25, 5, 'PZ001', 'Pizza Hut', 'https://example.com/pizza.jpg', true, true, 250, 80, 4.7, 55),
('Trà sữa trân châu', 'Trà sữa Đài Loan truyền thống', 35000.00, 30000.00, 90, 15, 'TS001', 'Gong Cha', 'https://example.com/tra-sua.jpg', true, true, 280, 95, 4.8, 70),
('Nước cam ép', 'Nước cam tươi nguyên chất', 25000.00, NULL, 120, 14, 'NC001', 'Vinamik', 'https://example.com/cam-ep.jpg', true, false, 150, 60, 4.5, 40),
('Sữa tươi không đường', 'Sữa tươi tiệt trùng', 18000.00, NULL, 150, 9, 'ST001', 'TH True Milk', 'https://example.com/sua-tuoi.jpg', true, false, 200, 120, 4.6, 85),
('Gà rán giòn', 'Gà rán thơm lừng giòn tan', 75000.00, 65000.00, 35, 5, 'GR001', 'KFC', 'https://example.com/ga-ran.jpg', true, true, 220, 70, 4.7, 50),
('Bánh croissant', 'Bánh sừng bò Pháp', 12000.00, NULL, 80, 3, 'CR001', 'Tous les Jours', 'https://example.com/croissant.jpg', true, false, 100, 45, 4.4, 30),
('Mì tôm Hảo Hảo', 'Mì tôm chua cay', 5000.00, NULL, 300, 11, 'MT001', 'Acecook', 'https://example.com/mi-tom.jpg', true, false, 500, 300, 4.3, 200),
('Xoài chín', 'Xoài cát Hòa Lộc ngọt lịm', 45000.00, 40000.00, 40, 6, 'XO001', 'Trái cây Việt', 'https://example.com/xoai.jpg', true, false, 120, 35, 4.6, 25),
('Rau muống', 'Rau muống tươi sạch', 8000.00, NULL, 200, 7, 'RM001', 'Rau sạch', 'https://example.com/rau-muong.jpg', true, false, 80, 50, 4.2, 35),
('Thịt heo ba chỉ', 'Thịt heo tươi ngon', 120000.00, 110000.00, 30, 8, 'TH001', 'CP', 'https://example.com/thit-heo.jpg', true, false, 90, 25, 4.5, 18),
('Cá basa phi lê', 'Cá basa tươi', 95000.00, NULL, 25, 8, 'CB001', 'Vĩnh Hoàn', 'https://example.com/ca-basa.jpg', true, false, 70, 20, 4.4, 15),
('Sữa chua vinamilk', 'Sữa chua Hy Lạp', 15000.00, NULL, 180, 21, 'SC001', 'Vinamilk', 'https://example.com/sua-chua.jpg', true, false, 250, 140, 4.7, 95),
('Mật ong hoa cà phê', 'Mật ong thiên nhiên', 180000.00, 160000.00, 20, 25, 'MO001', 'Mật ong Tây Nguyên', 'https://example.com/mat-ong.jpg', true, false, 60, 15, 4.8, 12),
('Kẹo dẻo Haribo', 'Kẹo dẻo đủ màu sắc', 35000.00, NULL, 100, 12, 'KD001', 'Haribo', 'https://example.com/keo-deo.jpg', true, false, 180, 90, 4.5, 60),
('Nước suối Lavie', 'Nước suối khoáng', 8000.00, NULL, 500, 17, 'NS001', 'Lavie', 'https://example.com/nuoc-suoi.jpg', true, false, 600, 400, 4.1, 250),
('Bánh Oreo', 'Bánh quy sô cô la', 25000.00, 22000.00, 120, 12, 'OR001', 'Oreo', 'https://example.com/oreo.jpg', true, false, 300, 180, 4.6, 120),
('Cà phê đen đá', 'Cà phê đen nguyên chất', 20000.00, NULL, 110, 16, 'CF002', 'Highlands', 'https://example.com/cf-den.jpg', true, false, 140, 55, 4.4, 38),
('Nước tăng lực Red Bull', 'Nước tăng lực', 20000.00, NULL, 150, 20, 'RB001', 'Red Bull', 'https://example.com/redbull.jpg', true, false, 320, 200, 4.3, 140),
('Bánh mì sandwich', 'Bánh mì sandwich đủ vị', 30000.00, 25000.00, 60, 3, 'SB001', 'KFC', 'https://example.com/sandwich.jpg', true, false, 160, 65, 4.5, 45),
('Thịt bò Wagyu', 'Thịt bò Nhật cao cấp', 500000.00, 450000.00, 10, 8, 'TB001', 'Wagyu', 'https://example.com/thit-bo.jpg', true, true, 50, 8, 4.9, 7),
('Rau cải ngọt', 'Rau cải tươi sạch', 10000.00, NULL, 180, 7, 'RC001', 'Rau sạch', 'https://example.com/rau-cai.jpg', true, false, 85, 52, 4.3, 38)
ON CONFLICT DO NOTHING;

INSERT INTO carts (user_id) VALUES
(2), (3), (4), (5), (6), (7), (8), (9), (10), (11), (12), (13), (14), (15), (16), (17), (18), (19), (20), (21), (22), (23), (24), (25)
ON CONFLICT DO NOTHING;

INSERT INTO cart_items (cart_id, product_id, quantity, price) VALUES
(2, 1, 2, 25000.00), (2, 2, 1, 30000.00), (2, 5, 1, 35000.00), (2, 6, 3, 15000.00),
(3, 3, 1, 45000.00), (3, 4, 2, 20000.00), (3, 7, 1, 25000.00),
(4, 6, 3, 15000.00), (4, 8, 1, 65000.00), (4, 10, 2, 35000.00),
(5, 9, 1, 180000.00), (5, 12, 2, 18000.00), (5, 15, 5, 5000.00),
(6, 11, 3, 25000.00), (6, 13, 1, 75000.00), (6, 14, 4, 12000.00),
(7, 16, 2, 45000.00), (7, 18, 1, 120000.00), (7, 19, 1, 95000.00),
(8, 20, 3, 15000.00), (8, 22, 1, 35000.00), (8, 23, 2, 8000.00),
(9, 24, 1, 25000.00), (9, 25, 2, 20000.00), (9, 26, 1, 20000.00),
(10, 27, 2, 30000.00), (10, 28, 1, 500000.00), (10, 29, 5, 10000.00),
(11, 1, 1, 25000.00), (11, 3, 1, 45000.00), (11, 5, 2, 35000.00),
(12, 2, 2, 30000.00), (12, 4, 1, 20000.00), (12, 6, 4, 15000.00),
(13, 7, 1, 25000.00), (13, 8, 1, 65000.00), (13, 9, 1, 180000.00),
(14, 10, 3, 35000.00), (14, 11, 2, 25000.00), (14, 12, 5, 18000.00),
(15, 13, 2, 75000.00), (15, 14, 6, 12000.00), (15, 15, 10, 5000.00),
(16, 16, 1, 45000.00), (16, 17, 3, 8000.00), (16, 18, 1, 120000.00),
(17, 19, 1, 95000.00), (17, 20, 4, 15000.00), (17, 21, 1, 180000.00),
(18, 22, 2, 35000.00), (18, 23, 6, 8000.00), (18, 24, 1, 25000.00),
(19, 25, 3, 20000.00), (19, 26, 2, 20000.00), (19, 27, 1, 30000.00),
(20, 28, 1, 500000.00), (20, 29, 8, 10000.00), (20, 1, 2, 25000.00),
(21, 2, 1, 30000.00), (21, 3, 1, 45000.00), (21, 4, 2, 20000.00),
(22, 5, 1, 35000.00), (22, 6, 5, 15000.00), (22, 7, 1, 25000.00),
(23, 8, 1, 65000.00), (23, 9, 1, 180000.00), (23, 10, 2, 35000.00),
(24, 11, 3, 25000.00), (24, 12, 4, 18000.00), (24, 13, 1, 75000.00),
(25, 14, 6, 12000.00), (25, 15, 8, 5000.00), (25, 16, 2, 45000.00)
ON CONFLICT DO NOTHING;

INSERT INTO orders (user_id, order_number, status, subtotal, tax_amount, shipping_cost, discount_amount, total, payment_method, payment_status, shipping_address, shipping_city, shipping_country, shipping_postal_code, notes) VALUES
(2, 'ORD001', 'COMPLETED', 80000.00, 8000.00, 20000.00, 5000.00, 103000.00, 'CASH', 'PAID', '456 Đường XYZ', 'Hà Nội', 'Vietnam', '10000', 'Giao hàng nhanh'),
(2, 'ORD002', 'PROCESSING', 95000.00, 9500.00, 20000.00, 0.00, 124500.00, 'CARD', 'PENDING', '456 Đường XYZ', 'Hà Nội', 'Vietnam', '10000', NULL),
(3, 'ORD003', 'PENDING', 65000.00, 6500.00, 20000.00, 10000.00, 81500.00, 'CASH', 'UNPAID', '789 Đường DEF', 'Đà Nẵng', 'Vietnam', '50000', 'Giao ban ngày'),
(4, 'ORD004', 'CANCELLED', 45000.00, 4500.00, 20000.00, 0.00, 69500.00, NULL, 'UNPAID', '321 Đường GHI', 'Hải Phòng', 'Vietnam', '18000', NULL),
(5, 'ORD005', 'COMPLETED', 220000.00, 22000.00, 20000.00, 22000.00, 240000.00, 'CARD', 'PAID', '159 Đường JKL', 'Cần Thơ', 'Vietnam', '94000', NULL),
(6, 'ORD006', 'PROCESSING', 150000.00, 15000.00, 20000.00, 15000.00, 170000.00, 'CASH', 'PAID', '753 Đường MNO', 'Nha Trang', 'Vietnam', '65000', 'Giao sáng'),
(7, 'ORD007', 'PENDING', 180000.00, 18000.00, 20000.00, 0.00, 218000.00, 'CARD', 'PENDING', '951 Đường PQR', 'Vũng Tàu', 'Vietnam', '79000', NULL),
(8, 'ORD008', 'COMPLETED', 120000.00, 12000.00, 20000.00, 12000.00, 140000.00, 'CASH', 'PAID', '357 Đường STU', 'Huế', 'Vietnam', '53000', NULL),
(9, 'ORD009', 'PROCESSING', 95000.00, 9500.00, 20000.00, 9500.00, 115500.00, 'CARD', 'PENDING', '258 Đường VWX', 'Quy Nhon', 'Vietnam', '59000', NULL),
(10, 'ORD010', 'PENDING', 110000.00, 11000.00, 20000.00, 0.00, 141000.00, 'CASH', 'UNPAID', '147 Đường YZA', 'Phan Thiết', 'Vietnam', '80000', 'Giao chiều'),
(11, 'ORD011', 'COMPLETED', 200000.00, 20000.00, 20000.00, 20000.00, 220000.00, 'CARD', 'PAID', '369 Đường BCD', 'Đà Lạt', 'Vietnam', '67000', NULL),
(12, 'ORD012', 'CANCELLED', 80000.00, 8000.00, 20000.00, 0.00, 108000.00, NULL, 'UNPAID', '741 Đường EFG', 'Buôn Ma Thuột', 'Vietnam', '63000', NULL),
(13, 'ORD013', 'COMPLETED', 165000.00, 16500.00, 20000.00, 16500.00, 185500.00, 'CASH', 'PAID', '852 Đường HIJ', 'Pleiku', 'Vietnam', '62000', NULL),
(14, 'ORD014', 'PROCESSING', 140000.00, 14000.00, 20000.00, 14000.00, 160000.00, 'CARD', 'PENDING', '963 Đường KLM', 'Long Xuyên', 'Vietnam', '90000', NULL),
(15, 'ORD015', 'PENDING', 175000.00, 17500.00, 20000.00, 0.00, 212500.00, 'CASH', 'UNPAID', '159 Đường NOP', 'Mỹ Tho', 'Vietnam', '92000', 'Giao nhanh'),
(16, 'ORD016', 'COMPLETED', 130000.00, 13000.00, 20000.00, 13000.00, 150000.00, 'CARD', 'PAID', '753 Đường QRS', 'Rạch Giá', 'Vietnam', '95000', NULL),
(17, 'ORD017', 'PROCESSING', 190000.00, 19000.00, 20000.00, 19000.00, 210000.00, 'CASH', 'PAID', '951 Đường TUV', 'Cà Mau', 'Vietnam', '97000', NULL),
(18, 'ORD018', 'PENDING', 100000.00, 10000.00, 20000.00, 10000.00, 120000.00, 'CARD', 'PENDING', '357 Đường WXY', 'Bạc Liêu', 'Vietnam', '96000', NULL),
(19, 'ORD019', 'COMPLETED', 155000.00, 15500.00, 20000.00, 15500.00, 175500.00, 'CASH', 'PAID', '258 Đường ZAB', 'Sóc Trăng', 'Vietnam', '98000', NULL),
(20, 'ORD020', 'PROCESSING', 145000.00, 14500.00, 20000.00, 14500.00, 165500.00, 'CARD', 'PENDING', '147 Đường CDE', 'Cao Lãnh', 'Vietnam', '91000', NULL),
(21, 'ORD021', 'PENDING', 125000.00, 12500.00, 20000.00, 0.00, 157500.00, 'CASH', 'UNPAID', '369 Đường FGH', 'Vĩnh Long', 'Vietnam', '93000', NULL),
(22, 'ORD022', 'COMPLETED', 135000.00, 13500.00, 20000.00, 13500.00, 155500.00, 'CARD', 'PAID', '741 Đường IJK', 'Bến Tre', 'Vietnam', '86000', NULL),
(23, 'ORD023', 'PROCESSING', 115000.00, 11500.00, 20000.00, 11500.00, 135500.00, 'CASH', 'PAID', '852 Đường LMN', 'Trà Vinh', 'Vietnam', '87000', NULL),
(24, 'ORD024', 'PENDING', 105000.00, 10500.00, 20000.00, 10500.00, 125500.00, 'CARD', 'PENDING', '963 Đường OPQ', 'Kiên Giang', 'Vietnam', '92000', NULL),
(25, 'ORD025', 'COMPLETED', 160000.00, 16000.00, 20000.00, 16000.00, 180000.00, 'CASH', 'PAID', '159 Đường RST', 'An Giang', 'Vietnam', '88000', NULL)
ON CONFLICT DO NOTHING;

INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
(1, 1, 2, 25000.00), (1, 2, 1, 30000.00), (1, 5, 1, 35000.00),
(2, 3, 1, 45000.00), (2, 5, 1, 35000.00), (2, 4, 1, 20000.00),
(3, 6, 2, 15000.00), (3, 8, 1, 65000.00), (3, 7, 1, 25000.00),
(4, 3, 1, 45000.00), (4, 1, 1, 25000.00),
(5, 9, 1, 180000.00), (5, 12, 2, 18000.00), (5, 15, 5, 5000.00),
(6, 11, 3, 25000.00), (6, 13, 1, 75000.00), (6, 14, 4, 12000.00),
(7, 16, 2, 45000.00), (7, 18, 1, 120000.00), (7, 19, 1, 95000.00),
(8, 20, 3, 15000.00), (8, 22, 1, 35000.00), (8, 23, 2, 8000.00),
(9, 24, 1, 25000.00), (9, 25, 2, 20000.00), (9, 26, 1, 20000.00),
(10, 27, 2, 30000.00), (10, 28, 1, 500000.00), (10, 29, 5, 10000.00),
(11, 1, 1, 25000.00), (11, 3, 1, 45000.00), (11, 5, 2, 35000.00), (11, 6, 3, 15000.00),
(12, 2, 2, 30000.00), (12, 4, 1, 20000.00), (12, 6, 4, 15000.00),
(13, 7, 1, 25000.00), (13, 8, 1, 65000.00), (13, 9, 1, 180000.00),
(14, 10, 3, 35000.00), (14, 11, 2, 25000.00), (14, 12, 5, 18000.00),
(15, 13, 2, 75000.00), (15, 14, 6, 12000.00), (15, 15, 10, 5000.00),
(16, 16, 1, 45000.00), (16, 17, 3, 8000.00), (16, 18, 1, 120000.00),
(17, 19, 1, 95000.00), (17, 20, 4, 15000.00), (17, 21, 1, 180000.00),
(18, 22, 2, 35000.00), (18, 23, 6, 8000.00), (18, 24, 1, 25000.00),
(19, 25, 3, 20000.00), (19, 26, 2, 20000.00), (19, 27, 1, 30000.00),
(20, 28, 1, 500000.00), (20, 29, 8, 10000.00), (20, 1, 2, 25000.00),
(21, 2, 1, 30000.00), (21, 3, 1, 45000.00), (21, 4, 2, 20000.00),
(22, 5, 1, 35000.00), (22, 6, 5, 15000.00), (22, 7, 1, 25000.00),
(23, 8, 1, 65000.00), (23, 9, 1, 180000.00), (23, 10, 2, 35000.00),
(24, 11, 3, 25000.00), (24, 12, 4, 18000.00), (24, 13, 1, 75000.00),
(25, 14, 6, 12000.00), (25, 15, 8, 5000.00), (25, 16, 2, 45000.00)
ON CONFLICT DO NOTHING;

INSERT INTO payments (order_id, amount, method, status) VALUES
(1, 103000.00, 'CASH', 'COMPLETED'), (2, 124500.00, 'CARD', 'PENDING'), (3, 81500.00, 'CASH', 'PENDING'),
(4, 69500.00, NULL, 'CANCELLED'), (5, 240000.00, 'CARD', 'COMPLETED'), (6, 170000.00, 'CASH', 'COMPLETED'),
(7, 218000.00, 'CARD', 'PENDING'), (8, 140000.00, 'CASH', 'COMPLETED'), (9, 115500.00, 'CARD', 'PENDING'),
(10, 141000.00, 'CASH', 'PENDING'), (11, 220000.00, 'CARD', 'COMPLETED'), (12, 108000.00, NULL, 'CANCELLED'),
(13, 185500.00, 'CASH', 'COMPLETED'), (14, 160000.00, 'CARD', 'PENDING'), (15, 212500.00, 'CASH', 'PENDING'),
(16, 150000.00, 'CARD', 'COMPLETED'), (17, 210000.00, 'CASH', 'COMPLETED'), (18, 120000.00, 'CARD', 'PENDING'),
(19, 175500.00, 'CASH', 'COMPLETED'), (20, 165500.00, 'CARD', 'PENDING'), (21, 157500.00, 'CASH', 'PENDING'),
(22, 155500.00, 'CARD', 'COMPLETED'), (23, 135500.00, 'CASH', 'COMPLETED'), (24, 125500.00, 'CARD', 'PENDING'),
(25, 180000.00, 'CASH', 'COMPLETED')
ON CONFLICT DO NOTHING;

INSERT INTO shippings (order_id, shipping_address, shipping_date, status) VALUES
(1, '456 Đường XYZ, Hà Nội', '2024-01-15 10:00:00', 'DELIVERED'),
(2, '456 Đường XYZ, Hà Nội', '2024-01-20 14:00:00', 'IN_TRANSIT'),
(3, '789 Đường DEF, Đà Nẵng', '2024-01-25 09:00:00', 'PENDING'),
(4, '321 Đường GHI, Hải Phòng', NULL, 'CANCELLED'),
(5, '159 Đường JKL, Cần Thơ', '2024-01-16 11:00:00', 'DELIVERED'),
(6, '753 Đường MNO, Nha Trang', '2024-01-17 08:00:00', 'DELIVERED'),
(7, '951 Đường PQR, Vũng Tàu', '2024-01-21 15:00:00', 'IN_TRANSIT'),
(8, '357 Đường STU, Huế', '2024-01-18 09:30:00', 'DELIVERED'),
(9, '258 Đường VWX, Quy Nhon', '2024-01-22 10:00:00', 'IN_TRANSIT'),
(10, '147 Đường YZA, Phan Thiết', '2024-01-26 11:00:00', 'PENDING'),
(11, '369 Đường BCD, Đà Lạt', '2024-01-19 14:00:00', 'DELIVERED'),
(12, '741 Đường EFG, Buôn Ma Thuột', NULL, 'CANCELLED'),
(13, '852 Đường HIJ, Pleiku', '2024-01-20 08:30:00', 'DELIVERED'),
(14, '963 Đường KLM, Long Xuyên', '2024-01-23 13:00:00', 'IN_TRANSIT'),
(15, '159 Đường NOP, Mỹ Tho', '2024-01-27 09:00:00', 'PENDING'),
(16, '753 Đường QRS, Rạch Giá', '2024-01-21 10:30:00', 'DELIVERED'),
(17, '951 Đường TUV, Cà Mau', '2024-01-24 15:00:00', 'DELIVERED'),
(18, '357 Đường WXY, Bạc Liêu', '2024-01-25 11:00:00', 'IN_TRANSIT'),
(19, '258 Đường ZAB, Sóc Trăng', '2024-01-22 09:00:00', 'DELIVERED'),
(20, '147 Đường CDE, Cao Lãnh', '2024-01-26 14:00:00', 'IN_TRANSIT'),
(21, '369 Đường FGH, Vĩnh Long', '2024-01-28 10:00:00', 'PENDING'),
(22, '741 Đường IJK, Bến Tre', '2024-01-23 08:00:00', 'DELIVERED'),
(23, '852 Đường LMN, Trà Vinh', '2024-01-24 12:00:00', 'DELIVERED'),
(24, '963 Đường OPQ, Kiên Giang', '2024-01-27 15:00:00', 'PENDING'),
(25, '159 Đường RST, An Giang', '2024-01-25 09:30:00', 'DELIVERED')
ON CONFLICT DO NOTHING;

INSERT INTO coupons (code, description, discount_type, discount_value, min_purchase_amount, max_discount_amount, start_date, expiry_date, usage_limit, usage_count, usage_per_user, is_active, applicable_to) VALUES
('GIAM10', 'Giảm 10% cho đơn hàng trên 100000', 'PERCENTAGE', 10.00, 100000.00, 50000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 100, 5, 3, true, 'ALL'),
('FREESHIP', 'Miễn phí vận chuyển', 'FIXED', 20000.00, 50000.00, 20000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 50, 2, 1, true, 'ALL'),
('GIAM50K', 'Giảm 50000 VNĐ cho đơn trên 200000', 'FIXED', 50000.00, 200000.00, 50000.00, '2024-01-01 00:00:00', '2025-06-30 23:59:59', 30, 1, 1, true, 'ALL'),
('NEWUSER', 'Giảm 15% cho người dùng mới', 'PERCENTAGE', 15.00, 0.00, 75000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 200, 10, 1, true, 'NEW_USER'),
('SUMMER20', 'Giảm 20% mùa hè', 'PERCENTAGE', 20.00, 150000.00, 100000.00, '2024-06-01 00:00:00', '2024-08-31 23:59:59', 80, 12, 2, true, 'ALL'),
('WINTER15', 'Giảm 15% mùa đông', 'PERCENTAGE', 15.00, 120000.00, 90000.00, '2024-12-01 00:00:00', '2025-02-28 23:59:59', 60, 8, 2, true, 'ALL'),
('SPRING25', 'Giảm 25% mùa xuân', 'PERCENTAGE', 25.00, 200000.00, 125000.00, '2024-03-01 00:00:00', '2024-05-31 23:59:59', 40, 5, 1, true, 'ALL'),
('AUTUMN18', 'Giảm 18% mùa thu', 'PERCENTAGE', 18.00, 180000.00, 108000.00, '2024-09-01 00:00:00', '2024-11-30 23:59:59', 50, 7, 1, true, 'ALL'),
('WEEKEND30', 'Giảm 30% cuối tuần', 'PERCENTAGE', 30.00, 250000.00, 150000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 100, 15, 1, true, 'ALL'),
('FLASH50', 'Flash sale giảm 50K', 'FIXED', 50000.00, 300000.00, 50000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 200, 25, 3, true, 'ALL'),
('VIP100', 'Giảm 100K cho VIP', 'FIXED', 100000.00, 500000.00, 100000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 50, 3, 1, true, 'VIP'),
('FIRST5', 'Giảm 5% đơn đầu', 'PERCENTAGE', 5.00, 50000.00, 25000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 500, 80, 1, true, 'NEW_USER'),
('BIRTHDAY', 'Giảm 20% sinh nhật', 'PERCENTAGE', 20.00, 100000.00, 80000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 300, 45, 1, true, 'ALL'),
('LOYALTY', 'Giảm 12% khách quen', 'PERCENTAGE', 12.00, 80000.00, 48000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 400, 60, 5, true, 'LOYAL'),
('STUDENT', 'Giảm 10% sinh viên', 'PERCENTAGE', 10.00, 60000.00, 30000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 600, 90, 3, true, 'STUDENT'),
('SENIOR', 'Giảm 15% người cao tuổi', 'PERCENTAGE', 15.00, 70000.00, 52500.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 250, 35, 2, true, 'SENIOR'),
('FAMILY', 'Giảm 25K gia đình', 'FIXED', 25000.00, 150000.00, 25000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 350, 55, 4, true, 'ALL'),
('EARLYBIRD', 'Giảm sớm 8%', 'PERCENTAGE', 8.00, 100000.00, 40000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 500, 70, 2, true, 'ALL'),
('LUNCH', 'Giảm 10% buổi trưa', 'PERCENTAGE', 10.00, 50000.00, 25000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 1000, 150, 5, true, 'ALL'),
('DINNER', 'Giảm 12% buổi tối', 'PERCENTAGE', 12.00, 60000.00, 36000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 800, 120, 4, true, 'ALL'),
('MORNING', 'Giảm 15% buổi sáng', 'PERCENTAGE', 15.00, 80000.00, 60000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 600, 85, 3, true, 'ALL')
ON CONFLICT (code) DO NOTHING;

INSERT INTO customer_addresses (user_id, address_label, full_name, phone_number, address_line1, address_line2, city, state, country, postal_code, is_default) VALUES
(2, 'Nhà', 'Nguyễn Văn A', '0902345678', '456 Đường XYZ', 'Phường 1', 'Hà Nội', 'Hà Nội', 'Vietnam', '10000', true),
(2, 'Công ty', 'Nguyễn Văn A', '0902345678', '789 Đường ABC', 'Tầng 5', 'Hà Nội', 'Hà Nội', 'Vietnam', '10000', false),
(3, 'Nhà', 'Trần Thị B', '0903456789', '789 Đường DEF', 'Phường 2', 'Đà Nẵng', 'Đà Nẵng', 'Vietnam', '50000', true),
(4, 'Nhà', 'Lê Văn C', '0904567890', '321 Đường GHI', 'Phường 3', 'Hải Phòng', 'Hải Phòng', 'Vietnam', '18000', true),
(5, 'Nhà', 'Phạm Thị D', '0905678901', '159 Đường JKL', 'Phường 4', 'Cần Thơ', 'Cần Thơ', 'Vietnam', '94000', true),
(5, 'Công ty', 'Phạm Thị D', '0905678901', '753 Đường MNO', 'Tầng 3', 'Cần Thơ', 'Cần Thơ', 'Vietnam', '94000', false),
(6, 'Nhà', 'Hoàng Văn E', '0906789012', '753 Đường MNO', 'Phường 5', 'Nha Trang', 'Khánh Hòa', 'Vietnam', '65000', true),
(7, 'Nhà', 'Võ Thị F', '0907890123', '951 Đường PQR', 'Phường 6', 'Vũng Tàu', 'Bà Rịa Vũng Tàu', 'Vietnam', '79000', true),
(8, 'Nhà', 'Đặng Văn G', '0908901234', '357 Đường STU', 'Phường 7', 'Huế', 'Thừa Thiên Huế', 'Vietnam', '53000', true),
(9, 'Nhà', 'Bùi Thị H', '0909012345', '258 Đường VWX', 'Phường 8', 'Quy Nhon', 'Bình Định', 'Vietnam', '59000', true),
(10, 'Nhà', 'Đỗ Văn I', '0910123456', '147 Đường YZA', 'Phường 9', 'Phan Thiết', 'Bình Thuận', 'Vietnam', '80000', true),
(11, 'Nhà', 'Lý Thị J', '0911234567', '369 Đường BCD', 'Phường 10', 'Đà Lạt', 'Lâm Đồng', 'Vietnam', '67000', true),
(12, 'Nhà', 'Trương Văn K', '0912345678', '741 Đường EFG', 'Phường 11', 'Buôn Ma Thuột', 'Đắk Lắk', 'Vietnam', '63000', true),
(13, 'Nhà', 'Ngô Thị L', '0913456789', '852 Đường HIJ', 'Phường 12', 'Pleiku', 'Gia Lai', 'Vietnam', '62000', true),
(14, 'Nhà', 'Lương Văn M', '0914567890', '963 Đường KLM', 'Phường 13', 'Long Xuyên', 'An Giang', 'Vietnam', '90000', true),
(15, 'Nhà', 'Vũ Thị N', '0915678901', '159 Đường NOP', 'Phường 14', 'Mỹ Tho', 'Tiền Giang', 'Vietnam', '92000', true),
(16, 'Nhà', 'Phan Văn O', '0916789012', '753 Đường QRS', 'Phường 15', 'Rạch Giá', 'Kiên Giang', 'Vietnam', '95000', true),
(17, 'Nhà', 'Dương Thị P', '0917890123', '951 Đường TUV', 'Phường 16', 'Cà Mau', 'Cà Mau', 'Vietnam', '97000', true),
(18, 'Nhà', 'Tôn Văn Q', '0918901234', '357 Đường WXY', 'Phường 17', 'Bạc Liêu', 'Bạc Liêu', 'Vietnam', '96000', true),
(19, 'Nhà', 'Mai Thị R', '0919012345', '258 Đường ZAB', 'Phường 18', 'Sóc Trăng', 'Sóc Trăng', 'Vietnam', '98000', true),
(20, 'Nhà', 'Hồ Văn S', '0920123456', '147 Đường CDE', 'Phường 19', 'Cao Lãnh', 'Đồng Tháp', 'Vietnam', '91000', true),
(21, 'Nhà', 'Chu Thị T', '0921234567', '369 Đường FGH', 'Phường 20', 'Vĩnh Long', 'Vĩnh Long', 'Vietnam', '93000', true),
(22, 'Nhà', 'Đinh Văn U', '0922345678', '741 Đường IJK', 'Phường 21', 'Bến Tre', 'Bến Tre', 'Vietnam', '86000', true),
(23, 'Nhà', 'Lâm Thị V', '0923456789', '852 Đường LMN', 'Phường 22', 'Trà Vinh', 'Trà Vinh', 'Vietnam', '87000', true),
(24, 'Nhà', 'Tạ Văn W', '0924567890', '963 Đường OPQ', 'Phường 23', 'Kiên Giang', 'Kiên Giang', 'Vietnam', '92000', true),
(25, 'Nhà', 'Đoàn Thị X', '0925678901', '159 Đường RST', 'Phường 24', 'An Giang', 'An Giang', 'Vietnam', '88000', true)
ON CONFLICT DO NOTHING;

INSERT INTO customer_preferences (user_id, email_notifications, sms_notifications, newsletter_subscription, preferred_language, preferred_currency) VALUES
(2, true, false, true, 'vi', 'VND'), (3, true, true, true, 'en', 'USD'), (4, false, false, false, 'vi', 'VND'),
(5, true, false, true, 'vi', 'VND'), (6, true, true, false, 'vi', 'VND'), (7, false, true, true, 'en', 'USD'),
(8, true, false, true, 'vi', 'VND'), (9, true, true, true, 'vi', 'VND'), (10, false, false, false, 'en', 'USD'),
(11, true, false, true, 'vi', 'VND'), (12, true, true, true, 'vi', 'VND'), (13, false, true, false, 'vi', 'VND'),
(14, true, false, true, 'vi', 'VND'), (15, true, true, true, 'en', 'USD'), (16, false, false, true, 'vi', 'VND'),
(17, true, false, false, 'vi', 'VND'), (18, true, true, true, 'vi', 'VND'), (19, false, true, true, 'en', 'USD'),
(20, true, false, true, 'vi', 'VND'), (21, true, true, false, 'vi', 'VND'), (22, false, false, true, 'vi', 'VND'),
(23, true, true, true, 'vi', 'VND'), (24, true, false, true, 'vi', 'VND'), (25, false, true, false, 'vi', 'VND')
ON CONFLICT DO NOTHING;

INSERT INTO promotions (name, description, promotion_type, discount_value, min_purchase_amount, start_date, end_date, is_active, priority, applicable_to, banner_image) VALUES
('Khuyến mãi cuối tuần', 'Giảm giá đặc biệt cuối tuần', 'PERCENTAGE', 20.00, 100000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 1, 'ALL', 'https://example.com/banner1.jpg'),
('Flash Sale', 'Sale nhanh chỉ trong 24h', 'FIXED', 50000.00, 150000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 2, 'ALL', 'https://example.com/banner2.jpg'),
('Khuyến mãi đồ uống', 'Giảm 30% cho tất cả đồ uống', 'PERCENTAGE', 30.00, 50000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 3, 'CATEGORY', 'https://example.com/banner3.jpg'),
('Giảm giá tháng 1', 'Khuyến mãi đầu năm', 'PERCENTAGE', 15.00, 80000.00, '2024-01-01 00:00:00', '2024-01-31 23:59:59', true, 4, 'ALL', 'https://example.com/banner4.jpg'),
('Giảm giá tháng 2', 'Khuyến mãi tháng 2', 'PERCENTAGE', 18.00, 90000.00, '2024-02-01 00:00:00', '2024-02-29 23:59:59', true, 5, 'ALL', 'https://example.com/banner5.jpg'),
('Giảm giá tháng 3', 'Khuyến mãi tháng 3', 'PERCENTAGE', 20.00, 100000.00, '2024-03-01 00:00:00', '2024-03-31 23:59:59', true, 6, 'ALL', 'https://example.com/banner6.jpg'),
('Giảm giá tháng 4', 'Khuyến mãi tháng 4', 'PERCENTAGE', 22.00, 110000.00, '2024-04-01 00:00:00', '2024-04-30 23:59:59', true, 7, 'ALL', 'https://example.com/banner7.jpg'),
('Giảm giá tháng 5', 'Khuyến mãi tháng 5', 'PERCENTAGE', 25.00, 120000.00, '2024-05-01 00:00:00', '2024-05-31 23:59:59', true, 8, 'ALL', 'https://example.com/banner8.jpg'),
('Giảm giá tháng 6', 'Khuyến mãi tháng 6', 'PERCENTAGE', 18.00, 90000.00, '2024-06-01 00:00:00', '2024-06-30 23:59:59', true, 9, 'ALL', 'https://example.com/banner9.jpg'),
('Giảm giá tháng 7', 'Khuyến mãi tháng 7', 'PERCENTAGE', 20.00, 100000.00, '2024-07-01 00:00:00', '2024-07-31 23:59:59', true, 10, 'ALL', 'https://example.com/banner10.jpg'),
('Giảm giá tháng 8', 'Khuyến mãi tháng 8', 'PERCENTAGE', 22.00, 110000.00, '2024-08-01 00:00:00', '2024-08-31 23:59:59', true, 11, 'ALL', 'https://example.com/banner11.jpg'),
('Giảm giá tháng 9', 'Khuyến mãi tháng 9', 'PERCENTAGE', 24.00, 130000.00, '2024-09-01 00:00:00', '2024-09-30 23:59:59', true, 12, 'ALL', 'https://example.com/banner12.jpg'),
('Giảm giá tháng 10', 'Khuyến mãi tháng 10', 'PERCENTAGE', 25.00, 140000.00, '2024-10-01 00:00:00', '2024-10-31 23:59:59', true, 13, 'ALL', 'https://example.com/banner13.jpg'),
('Giảm giá tháng 11', 'Khuyến mãi tháng 11', 'PERCENTAGE', 30.00, 150000.00, '2024-11-01 00:00:00', '2024-11-30 23:59:59', true, 14, 'ALL', 'https://example.com/banner14.jpg'),
('Giảm giá tháng 12', 'Khuyến mãi tháng 12', 'PERCENTAGE', 35.00, 160000.00, '2024-12-01 00:00:00', '2024-12-31 23:59:59', true, 15, 'ALL', 'https://example.com/banner15.jpg'),
('Khuyến mãi sản phẩm mới', 'Giảm 15% sản phẩm mới', 'PERCENTAGE', 15.00, 70000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 16, 'NEW_PRODUCT', 'https://example.com/banner16.jpg'),
('Khuyến mãi bánh ngọt', 'Giảm 20% bánh ngọt', 'PERCENTAGE', 20.00, 60000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 17, 'CATEGORY', 'https://example.com/banner17.jpg'),
('Khuyến mãi thức ăn nhanh', 'Giảm 25% thức ăn nhanh', 'PERCENTAGE', 25.00, 80000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 18, 'CATEGORY', 'https://example.com/banner18.jpg'),
('Khuyến mãi đặc biệt', 'Giảm 40K cho đơn trên 200K', 'FIXED', 40000.00, 200000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 19, 'ALL', 'https://example.com/banner19.jpg'),
('Khuyến mãi lớn', 'Giảm 60K cho đơn trên 300K', 'FIXED', 60000.00, 300000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 20, 'ALL', 'https://example.com/banner20.jpg'),
('Khuyến mãi siêu lớn', 'Giảm 100K cho đơn trên 500K', 'FIXED', 100000.00, 500000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 21, 'ALL', 'https://example.com/banner21.jpg'),
('Khuyến mãi đồ ăn vặt', 'Giảm 12% đồ ăn vặt', 'PERCENTAGE', 12.00, 50000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 22, 'CATEGORY', 'https://example.com/banner22.jpg'),
('Khuyến mãi trái cây', 'Giảm 18% trái cây', 'PERCENTAGE', 18.00, 60000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 23, 'CATEGORY', 'https://example.com/banner23.jpg'),
('Khuyến mãi rau củ', 'Giảm 10% rau củ', 'PERCENTAGE', 10.00, 40000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', true, 24, 'CATEGORY', 'https://example.com/banner24.jpg')
ON CONFLICT DO NOTHING;

INSERT INTO system_settings (setting_key, setting_value, setting_type, description, is_editable) VALUES
('site_name', 'Ecommerce Store', 'STRING', 'Tên website', true),
('site_email', 'contact@example.com', 'STRING', 'Email liên hệ', true),
('currency', 'VND', 'STRING', 'Đơn vị tiền tệ', true),
('tax_rate', '10', 'NUMBER', 'Thuế suất (%)', true),
('shipping_cost', '20000', 'NUMBER', 'Phí vận chuyển', true),
('min_order_amount', '50000', 'NUMBER', 'Giá trị đơn hàng tối thiểu', true),
('maintenance_mode', 'false', 'BOOLEAN', 'Chế độ bảo trì', true),
('max_login_attempts', '5', 'NUMBER', 'Số lần đăng nhập tối đa', false),
('session_timeout', '30', 'NUMBER', 'Thời gian timeout (phút)', true),
('max_cart_items', '50', 'NUMBER', 'Số sản phẩm tối đa trong giỏ', true),
('items_per_page', '20', 'NUMBER', 'Số sản phẩm mỗi trang', true),
('enable_registration', 'true', 'BOOLEAN', 'Cho phép đăng ký', true),
('require_email_verification', 'false', 'BOOLEAN', 'Yêu cầu xác thực email', true),
('default_language', 'vi', 'STRING', 'Ngôn ngữ mặc định', true),
('default_currency', 'VND', 'STRING', 'Tiền tệ mặc định', true),
('free_shipping_threshold', '200000', 'NUMBER', 'Ngưỡng miễn phí ship', true),
('max_discount_percentage', '50', 'NUMBER', 'Giảm giá tối đa (%)', true),
('enable_reviews', 'true', 'BOOLEAN', 'Cho phép đánh giá', true),
('enable_ratings', 'true', 'BOOLEAN', 'Cho phép xếp hạng', true),
('enable_wishlist', 'true', 'BOOLEAN', 'Cho phép yêu thích', true),
('enable_comparison', 'true', 'BOOLEAN', 'Cho phép so sánh', true),
('max_file_size', '5', 'NUMBER', 'Kích thước file tối đa (MB)', true),
('allowed_image_types', 'jpg,png,gif', 'STRING', 'Loại ảnh cho phép', true),
('enable_cache', 'true', 'BOOLEAN', 'Bật cache', true),
('cache_ttl', '3600', 'NUMBER', 'Thời gian cache (giây)', true),
('enable_notifications', 'true', 'BOOLEAN', 'Bật thông báo', true)
ON CONFLICT (setting_key) DO NOTHING;

INSERT INTO activity_logs (user_id, username, action, entity_type, entity_id, description, ip_address, user_agent) VALUES
(1, 'admin', 'LOGIN', 'USER', 1, 'Admin logged in', '192.168.1.1', 'Mozilla/5.0'),
(2, 'user1', 'CREATE_ORDER', 'ORDER', 1, 'Created new order ORD001', '192.168.1.2', 'Chrome/120.0'),
(2, 'user1', 'UPDATE_PROFILE', 'USER', 2, 'Updated profile information', '192.168.1.2', 'Chrome/120.0'),
(3, 'user2', 'ADD_TO_CART', 'CART', 2, 'Added product to cart', '192.168.1.3', 'Firefox/121.0'),
(1, 'admin', 'CREATE_PRODUCT', 'PRODUCT', 1, 'Created new product', '192.168.1.1', 'Mozilla/5.0'),
(1, 'admin', 'UPDATE_PRODUCT', 'PRODUCT', 1, 'Updated product details', '192.168.1.1', 'Mozilla/5.0'),
(2, 'user1', 'APPLY_COUPON', 'COUPON', 1, 'Applied coupon GIAM10', '192.168.1.2', 'Chrome/120.0'),
(3, 'user2', 'CANCEL_ORDER', 'ORDER', 4, 'Cancelled order ORD004', '192.168.1.3', 'Firefox/121.0'),
(4, 'user3', 'LOGIN', 'USER', 4, 'User logged in', '192.168.1.4', 'Edge/120.0'),
(5, 'user4', 'CREATE_ORDER', 'ORDER', 5, 'Created new order ORD005', '192.168.1.5', 'Safari/17.0'),
(6, 'user5', 'ADD_TO_CART', 'CART', 3, 'Added product to cart', '192.168.1.6', 'Chrome/121.0'),
(7, 'user6', 'REMOVE_FROM_CART', 'CART', 4, 'Removed product from cart', '192.168.1.7', 'Firefox/122.0'),
(8, 'user7', 'UPDATE_ADDRESS', 'ADDRESS', 8, 'Updated address', '192.168.1.8', 'Chrome/120.0'),
(9, 'user8', 'CHANGE_PASSWORD', 'USER', 9, 'Changed password', '192.168.1.9', 'Edge/121.0'),
(10, 'user9', 'VIEW_PRODUCT', 'PRODUCT', 5, 'Viewed product details', '192.168.1.10', 'Safari/17.0'),
(11, 'user10', 'SEARCH_PRODUCTS', 'PRODUCT', NULL, 'Searched for products', '192.168.1.11', 'Chrome/120.0'),
(12, 'user11', 'ADD_REVIEW', 'PRODUCT', 3, 'Added product review', '192.168.1.12', 'Firefox/121.0'),
(13, 'user12', 'RATE_PRODUCT', 'PRODUCT', 7, 'Rated product', '192.168.1.13', 'Chrome/121.0'),
(14, 'user13', 'UPDATE_ORDER', 'ORDER', 13, 'Updated order', '192.168.1.14', 'Edge/120.0'),
(15, 'user14', 'CANCEL_ORDER', 'ORDER', 14, 'Cancelled order', '192.168.1.15', 'Safari/17.0'),
(16, 'user15', 'APPLY_COUPON', 'COUPON', 2, 'Applied coupon FREESHIP', '192.168.1.16', 'Chrome/120.0'),
(17, 'user16', 'VIEW_CATEGORY', 'CATEGORY', 1, 'Viewed category', '192.168.1.17', 'Firefox/122.0'),
(18, 'user17', 'ADD_TO_WISHLIST', 'PRODUCT', 10, 'Added to wishlist', '192.168.1.18', 'Chrome/121.0'),
(19, 'user18', 'REMOVE_FROM_WISHLIST', 'PRODUCT', 12, 'Removed from wishlist', '192.168.1.19', 'Edge/121.0'),
(20, 'user19', 'UPDATE_PREFERENCES', 'PREFERENCE', 19, 'Updated preferences', '192.168.1.20', 'Safari/17.0'),
(21, 'user20', 'LOGIN', 'USER', 20, 'User logged in', '192.168.1.21', 'Chrome/120.0'),
(22, 'user21', 'CREATE_ORDER', 'ORDER', 21, 'Created new order', '192.168.1.22', 'Firefox/121.0'),
(23, 'user22', 'ADD_TO_CART', 'CART', 22, 'Added product to cart', '192.168.1.23', 'Chrome/121.0'),
(24, 'user23', 'UPDATE_PROFILE', 'USER', 23, 'Updated profile', '192.168.1.24', 'Edge/120.0'),
(25, 'user24', 'VIEW_PRODUCT', 'PRODUCT', 15, 'Viewed product', '192.168.1.25', 'Safari/17.0')
ON CONFLICT DO NOTHING;

INSERT INTO coupon_usage (coupon_id, user_id, order_id, discount_amount) VALUES
(1, 2, 1, 8000.00), (2, 2, 2, 20000.00), (1, 3, 3, 10000.00), (4, 2, NULL, 12000.00), (4, 3, NULL, 9750.00),
(1, 4, 4, 4500.00), (2, 5, 5, 20000.00), (3, 6, 6, 15000.00), (4, 7, NULL, 27000.00), (5, 8, 8, 28000.00),
(6, 9, 9, 14250.00), (7, 10, NULL, 16500.00), (8, 11, 11, 39600.00), (9, 12, NULL, 45000.00), (10, 13, 13, 41250.00),
(11, 14, 14, 28000.00), (12, 15, NULL, 60000.00), (13, 16, 16, 15000.00), (14, 17, 17, 42000.00), (15, 18, 18, 12000.00),
(16, 19, 19, 31000.00), (17, 20, 20, 29000.00), (18, 21, NULL, 18750.00), (19, 22, 22, 16200.00), (20, 23, 23, 13800.00),
(21, 24, NULL, 27000.00), (22, 25, 25, 24000.00)
ON CONFLICT DO NOTHING;
