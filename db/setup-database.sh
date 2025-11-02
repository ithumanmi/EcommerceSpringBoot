#!/bin/bash
# Bash script to create and populate PostgreSQL database
# Database: ecommercespringboot_db
# User: postgres
# Password: 123456
# Port: 5433 (host) -> 5432 (container)

CONTAINER_NAME="ecommerce-postgres"
DB_NAME="ecommercespringboot_db"
DB_USER="postgres"
DB_PASSWORD="123456"

echo "Checking if Docker container is running..."

# Check if Docker is available
if ! command -v docker &> /dev/null; then
    echo "Error: Docker is not installed or not in PATH"
    exit 1
fi

# Check if container exists
if ! docker ps -a --filter "name=$CONTAINER_NAME" --format "{{.Names}}" | grep -q "$CONTAINER_NAME"; then
    echo "Container '$CONTAINER_NAME' does not exist. Please run 'docker-compose up -d postgres' first."
    exit 1
fi

# Check if container is running
if ! docker ps --filter "name=$CONTAINER_NAME" --format "{{.Names}}" | grep -q "$CONTAINER_NAME"; then
    echo "Starting container '$CONTAINER_NAME'..."
    docker start $CONTAINER_NAME
    sleep 5
fi

echo "Waiting for PostgreSQL to be ready..."
for i in {1..30}; do
    if docker exec $CONTAINER_NAME pg_isready -U $DB_USER -d $DB_NAME &> /dev/null; then
        echo "PostgreSQL is ready!"
        break
    fi
    if [ $i -eq 30 ]; then
        echo "Error: PostgreSQL did not become ready in time"
        exit 1
    fi
    sleep 2
done

# Get the directory of this script
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
SCHEMA_FILE="$SCRIPT_DIR/database-postgres.sql"
INSERT_FILE="$SCRIPT_DIR/database-postgres-insert.sql"

# Check if files exist
if [ ! -f "$SCHEMA_FILE" ]; then
    echo "Error: Schema file not found: $SCHEMA_FILE"
    exit 1
fi

if [ ! -f "$INSERT_FILE" ]; then
    echo "Error: Insert file not found: $INSERT_FILE"
    exit 1
fi

echo ""
echo "Creating database schema..."
export PGPASSWORD=$DB_PASSWORD

# Copy schema file to container and execute
docker cp "$SCHEMA_FILE" "$CONTAINER_NAME:/tmp/schema.sql"
if docker exec -e PGPASSWORD=$DB_PASSWORD $CONTAINER_NAME psql -U $DB_USER -d postgres -f /tmp/schema.sql; then
    echo "Schema created successfully!"
else
    echo "Warning: Some schema commands may have failed (this is normal if tables already exist)"
fi

# Connect to the database and create it if it doesn't exist
echo ""
echo "Ensuring database exists..."
if ! docker exec -e PGPASSWORD=$DB_PASSWORD $CONTAINER_NAME psql -U $DB_USER -d postgres -tc "SELECT 1 FROM pg_database WHERE datname='$DB_NAME'" | grep -q 1; then
    docker exec -e PGPASSWORD=$DB_PASSWORD $CONTAINER_NAME psql -U $DB_USER -d postgres -c "CREATE DATABASE $DB_NAME"
    echo "Database '$DB_NAME' created!"
else
    echo "Database '$DB_NAME' already exists."
fi

# Copy and execute insert file
echo ""
echo "Inserting data..."
docker cp "$INSERT_FILE" "$CONTAINER_NAME:/tmp/insert.sql"
if docker exec -e PGPASSWORD=$DB_PASSWORD $CONTAINER_NAME psql -U $DB_USER -d $DB_NAME -f /tmp/insert.sql; then
    echo "Data inserted successfully!"
else
    echo "Error inserting data"
    exit 1
fi

echo ""
echo "Database setup completed successfully!"
echo "Database: $DB_NAME"
echo "User: $DB_USER"
echo "Host: localhost"
echo "Port: 5433"

