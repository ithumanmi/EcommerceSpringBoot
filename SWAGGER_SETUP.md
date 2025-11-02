# Swagger/OpenAPI Setup

## Setup Complete

Swagger UI has been configured for your Spring Boot application.

## Access Swagger UI

After starting your application, access Swagger UI at:

**URL:** http://localhost:8081/swagger-ui/index.html

**API Docs (JSON):** http://localhost:8081/v3/api-docs

## Features

- JWT Bearer Token authentication support
- All API endpoints documented
- Interactive API testing
- Request/Response schemas

## Using JWT Token in Swagger

1. First, authenticate using `/api/auth/login` endpoint
2. Copy the JWT token from the response
3. Click the "Authorize" button in Swagger UI (top right)
4. Enter: `Bearer <your-token>`
5. Click "Authorize" and "Close"
6. Now you can test authenticated endpoints

## Configuration

- **Config Class:** `com.example.ecommerce.config.SwaggerConfig`
- **Security:** Swagger endpoints are publicly accessible
- **Dependency:** `springdoc-openapi-starter-webmvc-ui:2.3.0`

## Next Steps

1. Run `mvn clean install` to download dependencies
2. Start your Spring Boot application
3. Open browser to http://localhost:8081/swagger-ui/index.html
4. Start testing your APIs!

