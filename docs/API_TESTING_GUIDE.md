# Authentication API Testing Guide

## Overview

Your Spring Boot E-commerce application now has fully functional **Register** and **Login** API endpoints with JWT authentication.

## API Endpoints

### Base URL
```
http://localhost:8081/api/auth
```
> Note: Your application is configured to run on port 8081 (check `application.properties`)

---

## 1. Register API

### Endpoint
```
POST /api/auth/register
```

### Description
Creates a new user account with default role "USER".

### Request Headers
```
Content-Type: application/json
```

### Request Body
```json
{
  "username": "johndoe",
  "password": "password123",
  "email": "johndoe@example.com",
  "fullName": "John Doe"
}
```

### Field Validations
- **username**: Required, 3-50 characters
- **password**: Required, minimum 6 characters
- **email**: Required, must be valid email format
- **fullName**: Optional, maximum 100 characters

### Success Response (200 OK)
```json
{
  "message": "User registered successfully. Please login.",
  "token": null,
  "username": null,
  "role": null
}
```

### Error Responses

#### 400 Bad Request - Username exists
```json
{
  "message": "Username already exists",
  "token": null,
  "username": null,
  "role": null
}
```

#### 400 Bad Request - Email exists
```json
{
  "message": "Email already exists",
  "token": null,
  "username": null,
  "role": null
}
```

#### 400 Bad Request - Validation Error
```json
{
  "message": "Validation error message",
  "token": null,
  "username": null,
  "role": null
}
```

### cURL Example
```bash
curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "password": "password123",
    "email": "johndoe@example.com",
    "fullName": "John Doe"
  }'
```

---

## 2. Login API

### Endpoint
```
POST /api/auth/login
```

### Description
Authenticates a user and returns a JWT token.

### Request Headers
```
Content-Type: application/json
```

### Request Body
```json
{
  "username": "johndoe",
  "password": "password123"
}
```

### Field Validations
- **username**: Required, 3-50 characters
- **password**: Required, minimum 6 characters

### Success Response (200 OK)
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNjk5...",
  "username": "johndoe",
  "role": "USER",
  "message": null
}
```

### Error Responses

#### 400 Bad Request - Invalid Credentials
```json
{
  "message": "Invalid username or password",
  "token": null,
  "username": null,
  "role": null
}
```

#### 400 Bad Request - Validation Error
```json
{
  "message": "Username and password are required",
  "token": null,
  "username": null,
  "role": null
}
```

### cURL Example
```bash
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "password": "password123"
  }'
```

---

## 3. Using JWT Token for Protected Endpoints

After successful login, use the JWT token to access protected endpoints.

### Request Headers
```
Authorization: Bearer <your-jwt-token>
Content-Type: application/json
```

### Example: Accessing Protected Endpoint
```bash
curl -X GET http://localhost:8081/api/products \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNjk5..." \
  -H "Content-Type: application/json"
```

---

## Testing with Postman

### 1. Register a New User

1. Create a new POST request to `http://localhost:8081/api/auth/register`
2. Go to **Headers** tab:
   - Key: `Content-Type`, Value: `application/json`
3. Go to **Body** tab:
   - Select **raw** and **JSON**
   - Paste the registration JSON
4. Click **Send**

### 2. Login

1. Create a new POST request to `http://localhost:8081/api/auth/login`
2. Set headers and body similar to register
3. Click **Send**
4. Copy the `token` value from the response

### 3. Use JWT Token

1. Create a new request to any protected endpoint
2. Go to **Headers** tab:
   - Key: `Authorization`, Value: `Bearer <paste-token-here>`
3. Click **Send**

---

## Security Configuration

### Public Endpoints (No Authentication Required)
- `/api/auth/register`
- `/api/auth/login`
- `/actuator/**`

### Protected Endpoints
- All other `/api/**` endpoints require authentication
- `/api/users/**` requires ADMIN role

### JWT Token
- **Validity**: 24 hours
- **Algorithm**: HS256 (HMAC with SHA-256)
- **Secret Key**: Configured in `application.properties`

---

## Common Issues and Solutions

### 1. Database Connection Error
**Issue**: Can't connect to database

**Solution**: 
- Check database is running
- Verify connection details in `application.properties`

### 2. 403 Forbidden
**Issue**: Access denied to endpoint

**Solution**:
- Ensure JWT token is included in Authorization header
- Check token format: `Bearer <token>`
- Verify token hasn't expired (24 hours)

### 3. 401 Unauthorized
**Issue**: Invalid or expired token

**Solution**:
- Login again to get a new token
- Check token is correctly copied (no extra spaces)

### 4. Username Already Exists
**Issue**: Cannot register with existing username

**Solution**:
- Choose a different username
- Or login with existing credentials

---

## Testing Flow

### Complete Test Sequence

1. **Start Application**
   ```bash
   mvn spring-boot:run
   ```

2. **Register New User**
   ```bash
   curl -X POST http://localhost:8081/api/auth/register \
     -H "Content-Type: application/json" \
     -d '{
       "username": "testuser",
       "password": "test123",
       "email": "test@example.com",
       "fullName": "Test User"
     }'
   ```

3. **Login**
   ```bash
   curl -X POST http://localhost:8081/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{
       "username": "testuser",
       "password": "test123"
     }'
   ```

4. **Copy Token from Response**

5. **Access Protected Endpoint**
   ```bash
   curl -X GET http://localhost:8081/api/categories \
     -H "Authorization: Bearer YOUR_TOKEN_HERE"
   ```

---

## Additional Notes

### Password Security
- Passwords are hashed using BCrypt before storage
- Never store plain text passwords
- Minimum 6 characters required (increase for production)

### Default Role
- All registered users get "USER" role by default
- Admin users must be created manually in the database

### CORS Configuration
- Currently allows all origins (`@CrossOrigin(origins = "*")`)
- Configure properly for production environments

### Token Refresh
- Current implementation doesn't support token refresh
- Users must login again after token expires (24 hours)

---

## Next Steps

1. ✅ Registration API - **COMPLETED**
2. ✅ Login API - **COMPLETED**
3. ✅ JWT Authentication - **COMPLETED**
4. 🔄 Consider adding:
   - Token refresh endpoint
   - Password reset functionality
   - Email verification
   - Account activation
   - Role-based access control enhancements
   - Rate limiting for login attempts

---

## Files Created/Modified

### New Files
- `src/main/java/com/example/ecommerce/dto/RegisterRequest.java` - Registration DTO

### Modified Files
- `src/main/java/com/example/ecommerce/controller/AuthController.java` - Updated to use RegisterRequest
- `src/main/java/com/example/ecommerce/service/AuthService.java` - Updated register method

### Existing Files (Already Implemented)
- `src/main/java/com/example/ecommerce/dto/LoginRequest.java`
- `src/main/java/com/example/ecommerce/dto/AuthResponse.java`
- `src/main/java/com/example/ecommerce/security/JwtUtil.java`
- `src/main/java/com/example/ecommerce/security/JwtRequestFilter.java`
- `src/main/java/com/example/ecommerce/security/SecurityConfig.java`
- `src/main/java/com/example/ecommerce/security/CustomUserDetailsService.java`

---

**Happy Testing! 🚀**

