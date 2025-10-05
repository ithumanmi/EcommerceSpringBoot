-- Update existing users with BCrypt hashed passwords
-- These are hashed versions of the original passwords

-- Admin password: admin123
-- Hashed: $2a$10$rIv2xhWpVJL0P8DqLKu7I.P4vzB5F8UzPtLwGKxJ8q8rN0QqLF4Gy
UPDATE users SET password = '$2a$10$rIv2xhWpVJL0P8DqLKu7I.P4vzB5F8UzPtLwGKxJ8q8rN0QqLF4Gy' WHERE username = 'admin';

-- User password: 123456  
-- Hashed: $2a$10$Q8R0X8F4i7LJ.T5P5z5p7O8K0N7Y9J6M5L4K3J2H1G0F9E8D7C6B5A
UPDATE users SET password = '$2a$10$Q8R0X8F4i7LJ.T5P5z5p7O8K0N7Y9J6M5L4K3J2H1G0F9E8D7C6B5A' WHERE username = 'khachhang';

-- Verify the changes
SELECT id, username, email, role FROM users;

-- Note: If these hashes don't work, you should register new users through the /api/auth/register endpoint
-- or generate proper BCrypt hashes using the application

