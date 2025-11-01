# Docker Setup for E-commerce Spring Boot Application

This document provides instructions for running the e-commerce Spring Boot application using Docker with PostgreSQL database.

## Prerequisites

- Docker Desktop installed and running
- Docker Compose (included with Docker Desktop)

## Quick Start

### 1. Build and Run with Docker Compose

```bash
# Build and start all services
docker-compose up --build

# Run in detached mode (background)
docker-compose up -d --build
```

### 2. Access the Application

- **Application**: http://localhost:8080
- **Database**: localhost:5432
- **Health Check**: http://localhost:8080/actuator/health

## Services

### PostgreSQL Database
- **Container**: ecommerce-postgres
- **Port**: 5432
- **Database**: ecommercespringboot_db
- **Username**: postgres
- **Password**: 123456

### Spring Boot Application
- **Container**: ecommerce-app
- **Port**: 8080
- **Health Check**: Enabled with 30s intervals

## Development Mode

For development with hot reload and debugging:

```bash
# Use development configuration
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up --build

# Or run in detached mode
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up -d --build
```

Development mode includes:
- Debug port 5005 exposed
- Source code mounted for hot reload
- Development profile active

## Common Commands

### View Logs
```bash
# All services
docker-compose logs

# Specific service
docker-compose logs app
docker-compose logs mysql

# Follow logs
docker-compose logs -f app
```

### Stop Services
```bash
# Stop all services
docker-compose down

# Stop and remove volumes (WARNING: This will delete database data)
docker-compose down -v
```

### Rebuild Application
```bash
# Rebuild only the app service
docker-compose build app

# Rebuild and restart
docker-compose up --build app
```

### Database Access
```bash
# Connect to PostgreSQL container
docker-compose exec postgres psql -U postgres -d ecommercespringboot_db

# Or connect with password
docker-compose exec postgres psql -U postgres -d ecommercespringboot_db -W
```

## Environment Variables

The application uses the following environment variables:

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | Database connection URL | jdbc:postgresql://postgres:5432/ecommercespringboot_db |
| `SPRING_DATASOURCE_USERNAME` | Database username | postgres |
| `SPRING_DATASOURCE_PASSWORD` | Database password | 123456 |
| `JWT_SECRET` | JWT signing secret | mySecretKeyForJWTTokenGenerationThatNeedsToBeAtLeast256BitsLongForHS256Algorithm |

## Troubleshooting

### Application Won't Start
1. Check if PostgreSQL is healthy: `docker-compose ps`
2. View application logs: `docker-compose logs app`
3. Ensure port 8080 is not in use by another application

### Database Connection Issues
1. Wait for PostgreSQL to be ready: `docker-compose logs postgres`
2. Check if the database exists: `docker-compose exec postgres psql -U postgres -l`

### Port Conflicts
If ports 8080 or 5432 are already in use, modify the ports in `docker-compose.yml`:
```yaml
ports:
  - "8081:8080"  # Change host port to 8081
  - "5433:5432"  # Change PostgreSQL port to 5433
```

## Production Considerations

For production deployment:

1. **Change default passwords** in environment variables
2. **Use secrets management** for sensitive data
3. **Enable SSL/TLS** for database connections
4. **Use external volume mounts** for persistent data
5. **Configure proper logging** levels
6. **Set up monitoring** and alerting

## File Structure

```
├── Dockerfile                 # Multi-stage build for Spring Boot app
├── docker-compose.yml         # Main compose file
├── docker-compose.dev.yml     # Development overrides
├── .dockerignore              # Files to exclude from Docker context
├── database-postgres.sql      # PostgreSQL database initialization script
└── DOCKER_SETUP.md           # This documentation
```

## API Endpoints

Once the application is running, you can access:

- **Authentication**: POST /api/auth/login
- **Products**: GET /api/products
- **Categories**: GET /api/categories
- **Health Check**: GET /actuator/health
- **API Documentation**: Available at /swagger-ui.html (if enabled)
