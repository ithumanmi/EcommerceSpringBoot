# Spring Boot E-commerce Backend with Authentication

## What has been added:

### 1. Dependencies (pom.xml)
- Spring Security
- JWT (JSON Web Token) for authentication
- Password encryption with BCrypt

### 2. Security Configuration
- JWT-based authentication
- Stateless session management
- Public endpoints: `/api/auth/**`
- Protected endpoints: Requires authentication
- Admin-only endpoints: `/api/users/**`

### 3. Authentication Endpoints

#### Register a new user
```
POST /api/auth/register
Content-Type: application/json

{
    "username": "john",
    "password": "password123",
    "email": "john@example.com",
    "fullName": "John Doe",
    "role": "USER"
}
```

#### Login
```
POST /api/auth/login
Content-Type: application/json

{
    "username": "john",
    "password": "password123"
}
```

Response:
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "username": "john",
    "role": "USER"
}
```

### 4. Using the JWT Token

For all protected endpoints, include the JWT token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

Example:
```
GET /api/products
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### 5. Testing with Postman/curl

#### Register:
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"testuser\",\"password\":\"test123\",\"email\":\"test@test.com\",\"fullName\":\"Test User\",\"role\":\"USER\"}"
```

#### Login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"testuser\",\"password\":\"test123\"}"
```

#### Access Protected Endpoint:
```bash
curl -X GET http://localhost:8080/api/products \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

### 6. Default Users (from database.sql)
- **Admin**: 
  - Username: `admin`
  - Password: `admin123` (need to be hashed when using)
- **User**: 
  - Username: `khachhang`
  - Password: `123456` (need to be hashed when using)

**Note**: The existing passwords in the database are plain text. You need to register new users or update existing passwords to hashed versions.

### 7. Security Features
-  JWT token-based authentication
-  Password encryption with BCrypt
-  Role-based access control (USER, ADMIN)
-  Token validation on each request
-  Stateless sessions
-  CORS enabled for all origins

### 8. Configuration
JWT secret key is configured in `application.properties`:
```properties
jwt.secret=mySecretKeyForJWTTokenGenerationThatNeedsToBeAtLeast256BitsLongForHS256Algorithm
```

Token validity: 24 hours

### 9. Project Structure
```
src/main/java/com/example/ecommerce/
 controller/
    AuthController.java          (Login/Register endpoints)
    ProductController.java       (Product CRUD)
    ...
 dto/
    LoginRequest.java           (Login request DTO)
    AuthResponse.java           (Auth response DTO)
 security/
    JwtUtil.java                 (JWT token generation/validation)
    JwtRequestFilter.java       (JWT filter for requests)
    CustomUserDetailsService.java (User authentication)
    SecurityConfig.java          (Security configuration)
 service/
    AuthService.java             (Authentication business logic)
    ...
 ...
```

### 10. Running the Application
1. Make sure MySQL is running
2. Update database credentials in `application.properties` if needed
3. Run the application:
   ```
   mvn spring-boot:run
   ```
4. Application will start on `http://localhost:8080`

### 11. API Flow
1. Register a new user or use existing credentials
2. Login to get JWT token
3. Use the token in Authorization header for all subsequent requests
4. Token expires after 24 hours - login again to get new token

### 12. Security Notes
- Change the JWT secret in production
- Use HTTPS in production
- Implement refresh tokens for better UX
- Add rate limiting to prevent brute force attacks
- Implement account lockout after failed attempts

