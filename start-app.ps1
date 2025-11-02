# Stop any running Java processes
Write-Host 'Stopping Java processes...' -ForegroundColor Yellow
Get-Process java -ErrorAction SilentlyContinue | Stop-Process -Force

# Stop Docker container if running
Write-Host 'Stopping Docker ecommerce-app container...' -ForegroundColor Yellow
docker stop ecommerce-app 2>&1 | Out-Null

# Wait for port 8081 to be free
Write-Host 'Waiting for port 8081 to be free...' -ForegroundColor Yellow
while ((netstat -ano | findstr ':8081.*LISTENING' | Measure-Object).Count -gt 0) {
    Start-Sleep -Seconds 2
}

# Ensure PostgreSQL is running
Write-Host 'Checking PostgreSQL...' -ForegroundColor Yellow
$postgresRunning = docker ps -a 2>&1 | Select-String 'ecommerce-postgres'
if (-not $postgresRunning) {
    Write-Host 'Starting PostgreSQL...' -ForegroundColor Yellow
    docker-compose up -d postgres
    Start-Sleep -Seconds 10
} else {
    Write-Host 'PostgreSQL is running' -ForegroundColor Green
}

# Build and start
Write-Host 'Starting Spring Boot application...' -ForegroundColor Green
mvn clean spring-boot:run
