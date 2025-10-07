@echo off
echo ============================================
echo Authentication API Testing Script
echo ============================================
echo.

set BASE_URL=http://localhost:8081/api/auth

echo [TEST 1] Registering new user...
echo.
curl -X POST %BASE_URL%/register ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"testuser\",\"password\":\"test123456\",\"email\":\"test@example.com\",\"fullName\":\"Test User\"}"
echo.
echo.

echo [TEST 2] Logging in...
echo.
curl -X POST %BASE_URL%/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"testuser\",\"password\":\"test123456\"}"
echo.
echo.

echo ============================================
echo Testing Complete!
echo ============================================
echo.
echo NOTE: Copy the token from the login response to test protected endpoints
echo.
pause

