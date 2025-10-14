# User Management Quick Reference

## Quick Start

### 1. Start the Application
```bash
# Start PostgreSQL database
docker-compose up -d postgres

# Run the Spring Boot application
./mvnw spring-boot:run
```

### 2. Test the APIs

#### Using cURL

**Register a new user:**
```bash
curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "Test@123",
    "email": "test@example.com",
    "fullName": "Test User"
  }'
```

**Login:**
```bash
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "Test@123"
  }'
```

**Get your profile:**
```bash
curl -X GET http://localhost:8081/api/users/me \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

#### Using Postman
1. Import the collection: `User-Management-APIs.postman_collection.json`
2. Login to get token (it will be saved automatically)
3. Test the endpoints

## Common SQL Queries

### Check Users Table
```sql
-- View all users
SELECT * FROM users;

-- View users with specific role
SELECT * FROM users WHERE role = 'ROLE_ADMIN';

-- Count active users
SELECT COUNT(*) FROM users WHERE is_active = true;

-- Find user by username
SELECT * FROM users WHERE username = 'admin';

-- Find user by email
SELECT * FROM users WHERE email = 'admin@example.com';
```

### User Statistics
```sql
-- Total users
SELECT COUNT(*) as total_users FROM users;

-- Active vs Inactive users
SELECT 
    is_active,
    COUNT(*) as count 
FROM users 
GROUP BY is_active;

-- Users by role
SELECT 
    role,
    COUNT(*) as count 
FROM users 
GROUP BY role;

-- Recently registered users
SELECT username, email, created_at 
FROM users 
ORDER BY created_at DESC 
LIMIT 10;
```

### Create Admin User (Manual)
```sql
-- Create admin user with encrypted password
-- Password: admin123 (BCrypt encrypted)
INSERT INTO users (username, password, email, full_name, role, is_active, created_at, updated_at)
VALUES (
    'admin',
    '$2a$10$XptfskVJM0RdJr5V2Tx.Ae.9CvNvVJCd3dKC.HGOdHkKBD/6PwKYa',
    'admin@example.com',
    'System Administrator',
    'ROLE_ADMIN',
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
```

## API Endpoints Summary

### Public (Authenticated Users)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users/me` | Get current user profile |
| PUT | `/api/users/me` | Update current user profile |
| PUT | `/api/users/me/change-password` | Change password |

### Admin Only
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/paginated` | Get users with pagination |
| GET | `/api/users/{id}` | Get user by ID |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |
| GET | `/api/users/search` | Search users |
| GET | `/api/users/role/{role}` | Get users by role |
| PUT | `/api/users/{id}/role` | Update user role |
| PUT | `/api/users/{id}/activate` | Activate user |
| PUT | `/api/users/{id}/deactivate` | Deactivate user |
| GET | `/api/users/statistics` | Get user statistics |

## Common Scenarios

### Scenario 1: User Self-Service
1. User registers account
2. User logs in
3. User views profile
4. User updates profile information
5. User changes password

### Scenario 2: Admin User Management
1. Admin logs in
2. Admin views all users
3. Admin searches for specific user
4. Admin updates user role
5. Admin activates/deactivates users
6. Admin views statistics

### Scenario 3: Password Management
**User changing own password:**
```json
PUT /api/users/me/change-password
{
  "currentPassword": "OldPass@123",
  "newPassword": "NewPass@123",
  "confirmPassword": "NewPass@123"
}
```

**Password Requirements:**
- Minimum 6 characters
- At least one digit
- At least one lowercase letter
- At least one uppercase letter
- At least one special character (@#$%^&+=)

## Response Examples

### Success Response
```json
{
  "message": "User updated successfully",
  "success": true
}
```

### Error Response (User Not Found)
```json
{
  "timestamp": "2024-01-20T10:30:00",
  "status": 404,
  "error": "User Not Found",
  "message": "User not found with ID: 123"
}
```

### Validation Error
```json
{
  "email": "Invalid email format",
  "fullName": "Full name cannot exceed 100 characters"
}
```

## Security Notes

### Authentication
- All endpoints (except `/api/auth/**`) require JWT token
- Token should be included in `Authorization` header
- Format: `Authorization: Bearer {token}`

### Authorization
- **ROLE_USER**: Can access own profile (`/api/users/me/**`)
- **ROLE_ADMIN**: Can access all user management endpoints

### Password Security
- Passwords are encrypted using BCrypt
- Passwords are never returned in API responses
- Current password verification required for password changes

## Troubleshooting

### Issue: 401 Unauthorized
**Solution:** Ensure you're sending valid JWT token in Authorization header

### Issue: 403 Forbidden
**Solution:** User doesn't have required role (ADMIN) for the endpoint

### Issue: 409 Conflict
**Solution:** Username or email already exists

### Issue: 400 Bad Request (Password change)
**Solutions:**
- Verify current password is correct
- Ensure new password meets requirements
- Confirm new password and confirm password match

## Testing Checklist

### User Profile Management
- [ ] Get current user profile
- [ ] Update profile information
- [ ] Change password with valid credentials
- [ ] Try invalid password change (should fail)
- [ ] Update email to existing email (should fail)

### Admin Operations
- [ ] Get all users
- [ ] Get users with pagination
- [ ] Search users by keyword
- [ ] Filter users by role
- [ ] Update user information
- [ ] Change user role
- [ ] Activate/deactivate user
- [ ] Delete user
- [ ] View statistics

### Security
- [ ] Access admin endpoint without token (should fail)
- [ ] Access admin endpoint with user token (should fail)
- [ ] Access user profile endpoint with valid token (should succeed)

## Performance Tips

1. **Use Pagination**: For large user lists, always use paginated endpoint
   ```
   GET /api/users/paginated?page=0&size=20
   ```

2. **Search Efficiently**: Use specific keywords for searching
   ```
   GET /api/users/search?keyword=john
   ```

3. **Filter by Role**: When looking for specific role users
   ```
   GET /api/users/role/ADMIN
   ```

## Data Migration

### Export Users
```sql
COPY (SELECT * FROM users) TO '/tmp/users_export.csv' WITH CSV HEADER;
```

### Backup Users Table
```sql
CREATE TABLE users_backup AS SELECT * FROM users;
```

### Restore from Backup
```sql
INSERT INTO users SELECT * FROM users_backup WHERE id NOT IN (SELECT id FROM users);
```

## Monitoring

### Check Active Sessions
Monitor active user sessions by tracking last_login timestamps:
```sql
SELECT 
    username,
    email,
    last_login,
    EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - last_login))/3600 as hours_since_login
FROM users
WHERE is_active = true
ORDER BY last_login DESC;
```

### User Activity Report
```sql
SELECT 
    DATE(created_at) as registration_date,
    COUNT(*) as new_users
FROM users
WHERE created_at >= CURRENT_DATE - INTERVAL '30 days'
GROUP BY DATE(created_at)
ORDER BY registration_date DESC;
```

## Support

For issues or questions:
1. Check the comprehensive guide: `USER_MANAGEMENT_GUIDE.md`
2. Review API documentation
3. Check application logs
4. Verify database schema

## Additional Resources

- **Main Documentation**: `USER_MANAGEMENT_GUIDE.md`
- **Postman Collection**: `User-Management-APIs.postman_collection.json`
- **Authentication Guide**: `AUTHENTICATION_GUIDE.md`
- **API Testing Guide**: `API_TESTING_GUIDE.md`

