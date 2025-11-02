# PowerShell script to create and populate PostgreSQL database
# Database: ecommercespringboot_db
# User: postgres
# Password: 123456
# Port: 5433 (host) -> 5432 (container)

$containerName = "ecommerce-postgres"
$dbName = "ecommercespringboot_db"
$dbUser = "postgres"
$dbPassword = "123456"

Write-Host "Checking if Docker container is running..." -ForegroundColor Cyan

# Check if Docker is available
$dockerAvailable = Get-Command docker -ErrorAction SilentlyContinue
if (-not $dockerAvailable) {
    Write-Host "Error: Docker is not installed or not in PATH" -ForegroundColor Red
    exit 1
}

# Check if container exists
$containerExists = docker ps -a --filter "name=$containerName" --format "{{.Names}}"
if (-not $containerExists) {
    Write-Host "Container '$containerName' does not exist. Please run 'docker-compose up -d postgres' first." -ForegroundColor Yellow
    exit 1
}

# Check if container is running
$containerRunning = docker ps --filter "name=$containerName" --format "{{.Names}}"
if (-not $containerRunning) {
    Write-Host "Starting container '$containerName'..." -ForegroundColor Yellow
    docker start $containerName
    Start-Sleep -Seconds 5
}

Write-Host "Waiting for PostgreSQL to be ready..." -ForegroundColor Cyan
$maxAttempts = 30
$attempt = 0
do {
    $attempt++
    $result = docker exec $containerName pg_isready -U $dbUser -d $dbName 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "PostgreSQL is ready!" -ForegroundColor Green
        break
    }
    if ($attempt -ge $maxAttempts) {
        Write-Host "Error: PostgreSQL did not become ready in time" -ForegroundColor Red
        exit 1
    }
    Start-Sleep -Seconds 2
} while ($true)

# Get the directory of this script
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$schemaFile = Join-Path $scriptDir "database-postgres.sql"
$insertFile = Join-Path $scriptDir "database-postgres-insert.sql"

# Check if files exist
if (-not (Test-Path $schemaFile)) {
    Write-Host "Error: Schema file not found: $schemaFile" -ForegroundColor Red
    exit 1
}

if (-not (Test-Path $insertFile)) {
    Write-Host "Error: Insert file not found: $insertFile" -ForegroundColor Red
    exit 1
}

Write-Host "`nCreating database schema..." -ForegroundColor Cyan
$env:PGPASSWORD = $dbPassword
$schemaResult = docker exec -e PGPASSWORD=$dbPassword $containerName psql -U $dbUser -d postgres -f /tmp/schema.sql 2>&1

# Copy schema file to container and execute
docker cp $schemaFile "$containerName`:/tmp/schema.sql" 2>&1 | Out-Null
$schemaOutput = docker exec -e PGPASSWORD=$dbPassword $containerName psql -U $dbUser -d postgres -f /tmp/schema.sql 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "Schema created successfully!" -ForegroundColor Green
} else {
    Write-Host "Warning: Some schema commands may have failed (this is normal if tables already exist)" -ForegroundColor Yellow
    Write-Host $schemaOutput
}

# Connect to the database and create it if it doesn't exist
Write-Host "`nEnsuring database exists..." -ForegroundColor Cyan
docker exec -e PGPASSWORD=$dbPassword $containerName psql -U $dbUser -d postgres -c "SELECT 1 FROM pg_database WHERE datname='$dbName'" | Out-Null
if ($LASTEXITCODE -ne 0 -or $LASTEXITCODE -ne 0) {
    docker exec -e PGPASSWORD=$dbPassword $containerName psql -U $dbUser -d postgres -c "CREATE DATABASE $dbName" 2>&1 | Out-Null
    Write-Host "Database '$dbName' created!" -ForegroundColor Green
} else {
    Write-Host "Database '$dbName' already exists." -ForegroundColor Yellow
}

# Copy and execute insert file
Write-Host "`nInserting data..." -ForegroundColor Cyan
docker cp $insertFile "$containerName`:/tmp/insert.sql" 2>&1 | Out-Null
$insertOutput = docker exec -e PGPASSWORD=$dbPassword $containerName psql -U $dbUser -d $dbName -f /tmp/insert.sql 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "Data inserted successfully!" -ForegroundColor Green
} else {
    Write-Host "Error inserting data:" -ForegroundColor Red
    Write-Host $insertOutput
    exit 1
}

Write-Host "`nDatabase setup completed successfully!" -ForegroundColor Green
Write-Host "Database: $dbName" -ForegroundColor Cyan
Write-Host "User: $dbUser" -ForegroundColor Cyan
Write-Host "Host: localhost" -ForegroundColor Cyan
Write-Host "Port: 5433" -ForegroundColor Cyan

