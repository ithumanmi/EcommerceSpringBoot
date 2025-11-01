# User Management Module Documentation

## Overview
This document describes the comprehensive User Management Module implemented for the E-commerce Spring Boot application. The module provides complete CRUD operations, user profile management, role-based access control, and user administration features.

## Features

### 1. User Profile Management
- View and update user profile
- Manage personal information (name, email, phone, address)
- Change password with validation
- View profile statistics

### 2. User Administration (Admin Only)
- Create, read, update, and delete users
- Search and filter users
- Activate/deactivate user accounts
- Manage user roles
- View user statistics

### 3. Security Features
- Password encryption using BCrypt
- Role-based access control (RBAC)
- JWT token authentication
- Secure password change with current password verification

## Database Schema

### User Entity Fields
```
- id (Long): Primary key
- username (String): Unique username (3-50 characters)
- password (String): Encrypted password (6-100 characters)
- email (String): Unique email address
- fullName (String): User's full name (max 100 characters)
- role (String): User role (default: ROLE_USER)
- phoneNumber (String): Contact phone number
- address (String): Street address
- city (String): City
- country (String): Country
- postalCode (String): Postal/ZIP code
- isActive (Boolean): Account active status (default: true)
- createdAt (LocalDateTime): Account creation timestamp
- updatedAt (LocalDateTime): Last update timestamp
- lastLogin (LocalDateTime): Last login timestamp
```

## API Endpoints

### Public Endpoints (Authenticated Users)

#### Get Current User Profile
```http
GET /api/users/me
Authorization: Bearer {token}
```

**Response:**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "fullName": "John Doe",
  "role": "ROLE_USER",
  "phoneNumber": "+1234567890",
  "address": "123 Main St",
  "city": "New York",
  "country": "USA",
  "postalCode": "10001",
  "isActive": true,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-20T15:45:00",
  "lastLogin": "2024-01-20T09:15:00"
}
```

#### Update Current User Profile
```http
PUT /api/users/me
Authorization: Bearer {token}
Content-Type: application/json

{
  "fullName": "John Smith",
  "email": "john.smith@example.com",
  "phoneNumber": "+1234567890",
  "address": "456 Oak Ave",
  "city": "Los Angeles",
  "country": "USA",
  "postalCode": "90001"
}
```

#### Change Password
```http
PUT /api/users/me/change-password
Authorization: Bearer {token}
Content-Type: application/json

{
  "currentPassword": "oldPassword123",
  "newPassword": "NewP@ssw0rd123",
  "confirmPassword": "NewP@ssw0rd123"
}
```

**Password Requirements:**
- Minimum 6 characters
- At least one digit
- At least one lowercase letter
- At least one uppercase letter
- At least one special character (@#$%^&+=)

### Admin Endpoints (ROLE_ADMIN Required)

#### Get All Users
```http
GET /api/users
Authorization: Bearer {admin_token}
```

#### Get Users with Pagination
```http
GET /api/users/paginated?page=0&size=10&sortBy=username&sortDir=ASC
Authorization: Bearer {admin_token}
```

**Query Parameters:**
- `page`: Page number (default: 0)
- `size`: Page size (default: 10)
- `sortBy`: Sort field (default: id)
- `sortDir`: Sort direction - ASC or DESC (default: ASC)

#### Get User by ID
```http
GET /api/users/{id}
Authorization: Bearer {admin_token}
```

#### Update User
```http
PUT /api/users/{id}
Authorization: Bearer {admin_token}
Content-Type: application/json

{
  "fullName": "Updated Name",
  "email": "updated@example.com",
  "phoneNumber": "+9876543210",
  "address": "789 Pine St",
  "city": "Chicago",
  "country": "USA",
  "postalCode": "60601"
}
```

#### Delete User
```http
DELETE /api/users/{id}
Authorization: Bearer {admin_token}
```

#### Search Users
```http
GET /api/users/search?keyword=john
Authorization: Bearer {admin_token}
```

Searches in: username, email, and full name

#### Get Users by Role
```http
GET /api/users/role/{role}
Authorization: Bearer {admin_token}
```

Example: `/api/users/role/USER` or `/api/users/role/ADMIN`

#### Update User Role
```http
PUT /api/users/{id}/role?role=ADMIN
Authorization: Bearer {admin_token}
```

#### Activate User
```http
PUT /api/users/{id}/activate
Authorization: Bearer {admin_token}
```

#### Deactivate User
```http
PUT /api/users/{id}/deactivate
Authorization: Bearer {admin_token}
```

#### Get User Statistics
```http
GET /api/users/statistics
Authorization: Bearer {admin_token}
```

**Response:**
```json
{
  "totalUsers": 150,
  "activeUsers": 142,
  "inactiveUsers": 8
}
```

## DTOs (Data Transfer Objects)

### UserProfileDTO
Complete user information (excluding password)
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "fullName": "John Doe",
  "role": "ROLE_USER",
  "phoneNumber": "+1234567890",
  "address": "123 Main St",
  "city": "New York",
  "country": "USA",
  "postalCode": "10001",
  "isActive": true,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-20T15:45:00",
  "lastLogin": "2024-01-20T09:15:00"
}
```

### UpdateUserDTO
For updating user information
```json
{
  "fullName": "John Smith",
  "email": "john.smith@example.com",
  "phoneNumber": "+1234567890",
  "address": "456 Oak Ave",
  "city": "Los Angeles",
  "country": "USA",
  "postalCode": "90001"
}
```

### ChangePasswordDTO
For changing user password
```json
{
  "currentPassword": "oldPassword123",
  "newPassword": "NewP@ssw0rd123",
  "confirmPassword": "NewP@ssw0rd123"
}
```

## Exception Handling

### UserNotFoundException
**Status Code:** 404 NOT FOUND
```json
{
  "timestamp": "2024-01-20T10:30:00",
  "status": 404,
  "error": "User Not Found",
  "message": "User not found with ID: 123"
}
```

### UserAlreadyExistsException
**Status Code:** 409 CONFLICT
```json
{
  "timestamp": "2024-01-20T10:30:00",
  "status": 409,
  "error": "User Already Exists",
  "message": "User already exists with email: john@example.com"
}
```

### InvalidPasswordException
**Status Code:** 400 BAD REQUEST
```json
{
  "timestamp": "2024-01-20T10:30:00",
  "status": 400,
  "error": "Invalid Password",
  "message": "Current password is incorrect"
}
```

## Security Configuration

### Role-Based Access Control
- **Public Access:** `/api/auth/**` - Authentication endpoints
- **Authenticated Users:** `/api/users/me/**` - Own profile management
- **Admin Only:** `/api/users/**` - User administration

### Authentication
All endpoints (except auth) require JWT authentication:
```
Authorization: Bearer {jwt_token}
```

## Service Layer Methods

### UserService Interface
```java
// Basic CRUD
List<User> getAllUsers();
User getUserById(Long id);
User createUser(User user);
User updateUser(Long id, UpdateUserDTO updateUserDTO);
void deleteUser(Long id);

// User search and query
User findByUsername(String username);
User findByEmail(String email);
List<User> searchUsers(String keyword);
Page<User> getAllUsersPaginated(Pageable pageable);

// User profile management
UserProfileDTO getUserProfile(Long id);
UserProfileDTO updateUserProfile(Long id, UpdateUserDTO updateUserDTO);

// Password management
void changePassword(Long userId, ChangePasswordDTO changePasswordDTO);

// User activation/deactivation
void activateUser(Long id);
void deactivateUser(Long id);

// Role management
void updateUserRole(Long id, String role);
List<User> getUsersByRole(String role);

// Statistics
long getTotalUsersCount();
long getActiveUsersCount();
```

## Testing Examples

### 1. Register a New User (via Auth)
```bash
curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "Pass@123",
    "email": "john@example.com",
    "fullName": "John Doe"
  }'
```

### 2. Get Current User Profile
```bash
curl -X GET http://localhost:8081/api/users/me \
  -H "Authorization: Bearer {your_token}"
```

### 3. Update Profile
```bash
curl -X PUT http://localhost:8081/api/users/me \
  -H "Authorization: Bearer {your_token}" \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "John Smith",
    "phoneNumber": "+1234567890",
    "address": "123 Main St",
    "city": "New York",
    "country": "USA",
    "postalCode": "10001"
  }'
```

### 4. Change Password
```bash
curl -X PUT http://localhost:8081/api/users/me/change-password \
  -H "Authorization: Bearer {your_token}" \
  -H "Content-Type: application/json" \
  -d '{
    "currentPassword": "Pass@123",
    "newPassword": "NewP@ss123",
    "confirmPassword": "NewP@ss123"
  }'
```

### 5. Admin: Get All Users (Paginated)
```bash
curl -X GET "http://localhost:8081/api/users/paginated?page=0&size=10&sortBy=username&sortDir=ASC" \
  -H "Authorization: Bearer {admin_token}"
```

### 6. Admin: Search Users
```bash
curl -X GET "http://localhost:8081/api/users/search?keyword=john" \
  -H "Authorization: Bearer {admin_token}"
```

### 7. Admin: Update User Role
```bash
curl -X PUT "http://localhost:8081/api/users/2/role?role=ADMIN" \
  -H "Authorization: Bearer {admin_token}"
```

### 8. Admin: Deactivate User
```bash
curl -X PUT http://localhost:8081/api/users/2/deactivate \
  -H "Authorization: Bearer {admin_token}"
```

### 9. Admin: Get Statistics
```bash
curl -X GET http://localhost:8081/api/users/statistics \
  -H "Authorization: Bearer {admin_token}"
```

## Best Practices

1. **Password Security**
   - Passwords are encrypted using BCrypt
   - Never expose passwords in API responses
   - Enforce strong password policies

2. **Data Validation**
   - Use Jakarta Bean Validation annotations
   - Validate on both DTO and entity levels
   - Return meaningful error messages

3. **Exception Handling**
   - Use custom exceptions for specific scenarios
   - Return appropriate HTTP status codes
   - Provide clear error messages

4. **Security**
   - Use role-based access control
   - Validate user permissions for each operation
   - Implement JWT token authentication

5. **Performance**
   - Use pagination for large datasets
   - Implement efficient search queries
   - Use proper indexing on database columns

## Architecture Components

### 1. Model Layer
- `User.java` - Entity with JPA annotations and validation

### 2. Repository Layer
- `UserRepository.java` - JPA repository with custom queries

### 3. Service Layer
- `UserService.java` - Service interface
- `UserServiceImpl.java` - Service implementation with business logic

### 4. Controller Layer
- `UserController.java` - REST API endpoints

### 5. DTO Layer
- `UserDTO.java` - Basic user data transfer
- `UserProfileDTO.java` - Complete user profile
- `UpdateUserDTO.java` - User update request
- `ChangePasswordDTO.java` - Password change request

### 6. Mapper Layer
- `UserMapper.java` - Entity-DTO conversion

### 7. Exception Layer
- `UserNotFoundException.java`
- `UserAlreadyExistsException.java`
- `InvalidPasswordException.java`
- `GlobalExceptionHandler.java` - Centralized exception handling

### 8. Security Layer
- `SecurityConfig.java` - Security configuration
- JWT authentication integration

## Future Enhancements

1. **Email Verification**
   - Send verification email on registration
   - Verify email before account activation

2. **Password Reset**
   - Forgot password functionality
   - Password reset via email

3. **Two-Factor Authentication (2FA)**
   - Enable 2FA for additional security
   - Support for authenticator apps

4. **User Activity Log**
   - Track user login history
   - Log user actions and changes

5. **Profile Picture**
   - Upload and manage profile pictures
   - Image storage integration

6. **Social Login**
   - OAuth2 integration (Google, Facebook, etc.)
   - Social media account linking

7. **Advanced Search**
   - Filter by multiple criteria
   - Date range filters
   - Export user data

## Conclusion

The User Management Module provides a complete solution for managing users in the e-commerce application. It includes all essential features for user administration, profile management, and security. The module is designed with scalability, security, and maintainability in mind.

