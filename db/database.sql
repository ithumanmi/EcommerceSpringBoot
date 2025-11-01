-- Tạo database
CREATE DATABASE IF NOT EXISTS ecommerce_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ecommerce_db;

-- Bảng người dùng
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    role VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Bảng sản phẩm
CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(12,2) NOT NULL,
    stock INT DEFAULT 0,
    category_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Bảng danh mục sản phẩm
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Bảng giỏ hàng
CREATE TABLE IF NOT EXISTS carts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Bảng mục giỏ hàng
CREATE TABLE IF NOT EXISTS cart_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cart_id BIGINT,
    product_id BIGINT,
    quantity INT DEFAULT 1,
    FOREIGN KEY (cart_id) REFERENCES carts(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Bảng đơn hàng
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(30) DEFAULT 'PENDING',
    total DECIMAL(12,2),
    FOREIGN KEY (user_id) REFERENCES users(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Bảng mục đơn hàng
CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT DEFAULT 1,
    price DECIMAL(12,2),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Bảng thanh toán
CREATE TABLE IF NOT EXISTS payments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(12,2),
    method VARCHAR(30),
    status VARCHAR(30),
    FOREIGN KEY (order_id) REFERENCES orders(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Bảng vận chuyển
CREATE TABLE IF NOT EXISTS shippings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    shipping_address VARCHAR(255),
    shipping_date TIMESTAMP,
    status VARCHAR(30),
    FOREIGN KEY (order_id) REFERENCES orders(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Bảng mã giảm giá
CREATE TABLE IF NOT EXISTS coupons (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    discount DECIMAL(5,2),
    expiry_date DATE,
    usage_limit INT DEFAULT 1
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Dữ liệu mẫu cho bảng người dùng
INSERT INTO users (username, password, email, full_name, role) VALUES
('admin', 'admin123', 'admin@example.com', 'Quản trị viên', 'ADMIN'),
('khachhang', '123456', 'khachhang@example.com', 'Nguyễn Văn A', 'USER');

-- Dữ liệu mẫu cho bảng danh mục sản phẩm
INSERT INTO categories (name, description) VALUES
('Đồ uống', 'Các loại nước giải khát, nước ép, cà phê'),
('Món ăn', 'Các món ăn chính, món phụ, món tráng miệng');

-- Dữ liệu mẫu cho bảng sản phẩm
INSERT INTO products (name, description, price, stock, category_id) VALUES
('Cà phê sữa đá', 'Cà phê pha với sữa đặc, đá viên', 25000, 100, 1),
('Trà đào cam sả', 'Trà đào kết hợp cam tươi và sả', 30000, 80, 1),
('Cơm tấm sườn', 'Cơm tấm với sườn nướng, bì, chả', 45000, 50, 2),
('Bánh flan', 'Bánh flan mềm mịn, vị caramel', 20000, 60, 2);

-- Dữ liệu mẫu cho bảng mã giảm giá
INSERT INTO coupons (code, discount, expiry_date, usage_limit) VALUES
('GIAM10', 10.00, '2025-12-31', 100),
('FREESHIP', 0.00, '2025-12-31', 50); 