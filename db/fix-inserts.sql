-- Fix users and coupons inserts
DELETE FROM users;
DELETE FROM coupons;

-- Insert users (without ON CONFLICT since table is empty)
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
('user25', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'user25@example.com', 'Hà Văn Y', 'ROLE_USER', '0926789012', '753 Đường UVW', 'Tây Ninh', 'Vietnam', '80000', true);

-- Insert coupons (without ON CONFLICT since table is empty)
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
('MORNING', 'Giảm 15% buổi sáng', 'PERCENTAGE', 15.00, 80000.00, 60000.00, '2024-01-01 00:00:00', '2025-12-31 23:59:59', 600, 85, 3, true, 'ALL');

