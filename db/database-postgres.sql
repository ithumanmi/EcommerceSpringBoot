-- PostgreSQL Database Schema for E-commerce Application
-- Create database and user (run as superuser)

-- Create database
CREATE DATABASE ecommercespringboot_db
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Connect to the database
\c ecommercespringboot_db;

-- Create extension for UUID generation (if needed)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create tables
-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    role VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Categories table
CREATE TABLE IF NOT EXISTS categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Products table
CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(12,2) NOT NULL,
    stock INTEGER DEFAULT 0,
    category_id BIGINT REFERENCES categories(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Carts table
CREATE TABLE IF NOT EXISTS carts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cart items table
CREATE TABLE IF NOT EXISTS cart_items (
    id BIGSERIAL PRIMARY KEY,
    cart_id BIGINT REFERENCES carts(id),
    product_id BIGINT REFERENCES products(id),
    quantity INTEGER DEFAULT 1
);

-- Orders table
CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(30) DEFAULT 'PENDING',
    total DECIMAL(12,2)
);

-- Order items table
CREATE TABLE IF NOT EXISTS order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT REFERENCES orders(id),
    product_id BIGINT REFERENCES products(id),
    quantity INTEGER DEFAULT 1,
    price DECIMAL(12,2)
);

-- Payments table
CREATE TABLE IF NOT EXISTS payments (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT REFERENCES orders(id),
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(12,2),
    method VARCHAR(30),
    status VARCHAR(30)
);

-- Shipping table
CREATE TABLE IF NOT EXISTS shippings (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT REFERENCES orders(id),
    shipping_address VARCHAR(255),
    shipping_date TIMESTAMP,
    status VARCHAR(30)
);

-- Coupons table
CREATE TABLE IF NOT EXISTS coupons (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    discount DECIMAL(5,2),
    expiry_date DATE,
    usage_limit INTEGER DEFAULT 1
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_products_category_id ON products(category_id);
CREATE INDEX IF NOT EXISTS idx_cart_items_cart_id ON cart_items(cart_id);
CREATE INDEX IF NOT EXISTS idx_cart_items_product_id ON cart_items(product_id);
CREATE INDEX IF NOT EXISTS idx_orders_user_id ON orders(user_id);
CREATE INDEX IF NOT EXISTS idx_order_items_order_id ON order_items(order_id);
CREATE INDEX IF NOT EXISTS idx_order_items_product_id ON order_items(product_id);
CREATE INDEX IF NOT EXISTS idx_payments_order_id ON payments(order_id);
CREATE INDEX IF NOT EXISTS idx_shippings_order_id ON shippings(order_id);
CREATE INDEX IF NOT EXISTS idx_coupons_code ON coupons(code);

-- Insert sample data
-- Users
INSERT INTO users (username, password, email, full_name, role) VALUES
('admin', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'admin@example.com', 'Quản trị viên', 'ADMIN'),
('khachhang', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'khachhang@example.com', 'Nguyễn Văn A', 'USER')
ON CONFLICT (username) DO NOTHING;

-- Categories
INSERT INTO categories (name, description) VALUES
('Đồ uống', 'Các loại nước giải khát, nước ép, cà phê'),
('Món ăn', 'Các món ăn chính, món phụ, món tráng miệng')
ON CONFLICT DO NOTHING;

-- Products
INSERT INTO products (name, description, price, stock, category_id) VALUES
('Cà phê sữa đá', 'Cà phê pha với sữa đặc, đá viên', 25000.00, 100, 1),
('Trà đào cam sả', 'Trà đào kết hợp cam tươi và sả', 30000.00, 80, 1),
('Cơm tấm sườn', 'Cơm tấm với sườn nướng, bì, chả', 45000.00, 50, 2),
('Bánh flan', 'Bánh flan mềm mịn, vị caramel', 20000.00, 60, 2)
ON CONFLICT DO NOTHING;

-- Coupons
INSERT INTO coupons (code, discount, expiry_date, usage_limit) VALUES
('GIAM10', 10.00, '2025-12-31', 100),
('FREESHIP', 0.00, '2025-12-31', 50)
ON CONFLICT (code) DO NOTHING;
