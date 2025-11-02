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
        role VARCHAR(20) DEFAULT 'ROLE_USER',
        phone_number VARCHAR(20),
        address VARCHAR(500),
        city VARCHAR(100),
        country VARCHAR(100),
        postal_code VARCHAR(20),
        is_active BOOLEAN DEFAULT true,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        last_login TIMESTAMP
    );

    -- Categories table
    CREATE TABLE IF NOT EXISTS categories (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Products table
    CREATE TABLE IF NOT EXISTS products (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description TEXT,
        price DECIMAL(12,2) NOT NULL,
        discount_price DECIMAL(12,2),
        stock INTEGER DEFAULT 0,
        category_id BIGINT REFERENCES categories(id),
        sku VARCHAR(50) UNIQUE,
        brand VARCHAR(100),
        image_url VARCHAR(500),
        is_active BOOLEAN DEFAULT true,
        is_featured BOOLEAN DEFAULT false,
        view_count INTEGER DEFAULT 0,
        sold_count INTEGER DEFAULT 0,
        rating DECIMAL(2,1),
        review_count INTEGER DEFAULT 0,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Carts table
    CREATE TABLE IF NOT EXISTS carts (
        id BIGSERIAL PRIMARY KEY,
        user_id BIGINT NOT NULL UNIQUE REFERENCES users(id),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Cart items table
    CREATE TABLE IF NOT EXISTS cart_items (
        id BIGSERIAL PRIMARY KEY,
        cart_id BIGINT NOT NULL REFERENCES carts(id),
        product_id BIGINT NOT NULL REFERENCES products(id),
        product_name VARCHAR(200),
        product_sku VARCHAR(50),
        product_image VARCHAR(500),
        quantity INTEGER NOT NULL DEFAULT 1,
        price DECIMAL(12,2),
        discount_price DECIMAL(12,2),
        subtotal DECIMAL(12,2),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Orders table
    CREATE TABLE IF NOT EXISTS orders (
        id BIGSERIAL PRIMARY KEY,
        user_id BIGINT NOT NULL REFERENCES users(id),
        order_number VARCHAR(50) UNIQUE,
        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
        subtotal DECIMAL(12,2),
        tax_amount DECIMAL(12,2),
        shipping_cost DECIMAL(12,2),
        discount_amount DECIMAL(12,2),
        total DECIMAL(12,2),
        payment_method VARCHAR(50),
        payment_status VARCHAR(30),
        shipping_address VARCHAR(500),
        shipping_city VARCHAR(100),
        shipping_country VARCHAR(100),
        shipping_postal_code VARCHAR(20),
        notes VARCHAR(1000),
        coupon_code VARCHAR(50),
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        cancelled_at TIMESTAMP,
        completed_at TIMESTAMP
    );

    -- Order items table
    CREATE TABLE IF NOT EXISTS order_items (
        id BIGSERIAL PRIMARY KEY,
        order_id BIGINT NOT NULL REFERENCES orders(id),
        product_id BIGINT NOT NULL REFERENCES products(id),
        product_name VARCHAR(200),
        product_sku VARCHAR(50),
        quantity INTEGER NOT NULL DEFAULT 1,
        price DECIMAL(12,2),
        discount_price DECIMAL(12,2),
        subtotal DECIMAL(12,2)
    );

    -- Payments table
    CREATE TABLE IF NOT EXISTS payments (
        id BIGSERIAL PRIMARY KEY,
        order_id BIGINT NOT NULL REFERENCES orders(id),
        payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        amount DECIMAL(12,2),
        method VARCHAR(30),
        status VARCHAR(30)
    );

    -- Shipping table
    CREATE TABLE IF NOT EXISTS shippings (
        id BIGSERIAL PRIMARY KEY,
        order_id BIGINT NOT NULL REFERENCES orders(id),
        shipping_address VARCHAR(255),
        shipping_date TIMESTAMP,
        status VARCHAR(30)
    );

    -- Coupons table
    CREATE TABLE IF NOT EXISTS coupons (
        id BIGSERIAL PRIMARY KEY,
        code VARCHAR(50) NOT NULL UNIQUE,
        description VARCHAR(200),
        discount_type VARCHAR(20) NOT NULL,
        discount_value DECIMAL(12,2) NOT NULL,
        min_purchase_amount DECIMAL(12,2),
        max_discount_amount DECIMAL(12,2),
        start_date TIMESTAMP,
        expiry_date TIMESTAMP,
        usage_limit INTEGER,
        usage_count INTEGER DEFAULT 0,
        usage_per_user INTEGER,
        is_active BOOLEAN DEFAULT true,
        applicable_to VARCHAR(50),
        applicable_category_ids VARCHAR(500),
        applicable_product_ids VARCHAR(500),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Customer addresses table
    CREATE TABLE IF NOT EXISTS customer_addresses (
        id BIGSERIAL PRIMARY KEY,
        user_id BIGINT NOT NULL REFERENCES users(id),
        address_label VARCHAR(100) NOT NULL,
        full_name VARCHAR(100) NOT NULL,
        phone_number VARCHAR(20) NOT NULL,
        address_line1 VARCHAR(500) NOT NULL,
        address_line2 VARCHAR(500),
        city VARCHAR(100) NOT NULL,
        state VARCHAR(100),
        country VARCHAR(100) NOT NULL,
        postal_code VARCHAR(20) NOT NULL,
        is_default BOOLEAN DEFAULT false,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Customer preferences table
    CREATE TABLE IF NOT EXISTS customer_preferences (
        id BIGSERIAL PRIMARY KEY,
        user_id BIGINT NOT NULL UNIQUE REFERENCES users(id),
        email_notifications BOOLEAN DEFAULT true,
        sms_notifications BOOLEAN DEFAULT false,
        newsletter_subscription BOOLEAN DEFAULT true,
        preferred_language VARCHAR(10) DEFAULT 'en',
        preferred_currency VARCHAR(10) DEFAULT 'USD',
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Promotions table
    CREATE TABLE IF NOT EXISTS promotions (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        description VARCHAR(500),
        promotion_type VARCHAR(50) NOT NULL,
        discount_value DECIMAL(12,2) NOT NULL,
        min_purchase_amount DECIMAL(12,2),
        start_date TIMESTAMP,
        end_date TIMESTAMP,
        is_active BOOLEAN DEFAULT true,
        priority INTEGER DEFAULT 0,
        applicable_to VARCHAR(50),
        banner_image VARCHAR(500),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- System settings table
    CREATE TABLE IF NOT EXISTS system_settings (
        id BIGSERIAL PRIMARY KEY,
        setting_key VARCHAR(100) NOT NULL UNIQUE,
        setting_value VARCHAR(1000),
        setting_type VARCHAR(50),
        description VARCHAR(500),
        is_editable BOOLEAN DEFAULT true,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Activity logs table
    CREATE TABLE IF NOT EXISTS activity_logs (
        id BIGSERIAL PRIMARY KEY,
        user_id BIGINT REFERENCES users(id),
        username VARCHAR(100),
        action VARCHAR(100) NOT NULL,
        entity_type VARCHAR(50),
        entity_id BIGINT,
        description VARCHAR(1000),
        ip_address VARCHAR(45),
        user_agent VARCHAR(500),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Coupon usage table
    CREATE TABLE IF NOT EXISTS coupon_usage (
        id BIGSERIAL PRIMARY KEY,
        coupon_id BIGINT NOT NULL REFERENCES coupons(id),
        user_id BIGINT NOT NULL REFERENCES users(id),
        order_id BIGINT REFERENCES orders(id),
        discount_amount DECIMAL(12,2),
        used_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- Create indexes for better performance
    CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
    CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
    CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
    CREATE INDEX IF NOT EXISTS idx_products_category_id ON products(category_id);
    CREATE INDEX IF NOT EXISTS idx_products_sku ON products(sku);
    CREATE INDEX IF NOT EXISTS idx_products_is_active ON products(is_active);
    CREATE INDEX IF NOT EXISTS idx_products_is_featured ON products(is_featured);
    CREATE INDEX IF NOT EXISTS idx_carts_user_id ON carts(user_id);
    CREATE INDEX IF NOT EXISTS idx_cart_items_cart_id ON cart_items(cart_id);
    CREATE INDEX IF NOT EXISTS idx_cart_items_product_id ON cart_items(product_id);
    CREATE INDEX IF NOT EXISTS idx_orders_user_id ON orders(user_id);
    CREATE INDEX IF NOT EXISTS idx_orders_status ON orders(status);
    CREATE INDEX IF NOT EXISTS idx_orders_order_number ON orders(order_number);
    CREATE INDEX IF NOT EXISTS idx_order_items_order_id ON order_items(order_id);
    CREATE INDEX IF NOT EXISTS idx_order_items_product_id ON order_items(product_id);
    CREATE INDEX IF NOT EXISTS idx_payments_order_id ON payments(order_id);
    CREATE INDEX IF NOT EXISTS idx_shippings_order_id ON shippings(order_id);
    CREATE INDEX IF NOT EXISTS idx_coupons_code ON coupons(code);
    CREATE INDEX IF NOT EXISTS idx_coupons_is_active ON coupons(is_active);
    CREATE INDEX IF NOT EXISTS idx_customer_addresses_user_id ON customer_addresses(user_id);
    CREATE INDEX IF NOT EXISTS idx_customer_preferences_user_id ON customer_preferences(user_id);
    CREATE INDEX IF NOT EXISTS idx_promotions_is_active ON promotions(is_active);
    CREATE INDEX IF NOT EXISTS idx_promotions_start_date ON promotions(start_date);
    CREATE INDEX IF NOT EXISTS idx_promotions_end_date ON promotions(end_date);
    CREATE INDEX IF NOT EXISTS idx_activity_logs_user_id ON activity_logs(user_id);
    CREATE INDEX IF NOT EXISTS idx_activity_logs_action ON activity_logs(action);
    CREATE INDEX IF NOT EXISTS idx_activity_logs_created_at ON activity_logs(created_at);
    CREATE INDEX IF NOT EXISTS idx_coupon_usage_coupon_id ON coupon_usage(coupon_id);
    CREATE INDEX IF NOT EXISTS idx_coupon_usage_user_id ON coupon_usage(user_id);
    CREATE INDEX IF NOT EXISTS idx_coupon_usage_order_id ON coupon_usage(order_id);
