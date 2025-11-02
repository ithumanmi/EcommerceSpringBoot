# DBeaver Connection Settings

## PostgreSQL Connection Details

**Connection Type:** PostgreSQL

**Settings:**
- **Host:** `localhost`
- **Port:** `5433`
- **Database:** ` `
- **Username:** `postgres`
- **Password:** `123456`

## Connection URL
```
jdbc:postgresql://localhost:5433/ecommercespringboot_db
```

## Steps to Connect in DBeaver:

1. Open DBeaver
2. Click "New Database Connection" (or Database > New Database Connection)
3. Select "PostgreSQL"
4. Fill in the connection settings:
   - Host: `localhost`
   - Port: `5433`
   - Database: `ecommercespringboot_db`
   - Username: `postgres`
   - Password: `123456`
5. Click "Test Connection" to verify
6. Click "Finish" to save and connect

## Common Issues:

1. **Container not running:** Make sure Docker container is running
   ```bash
   docker ps | grep ecommerce-postgres
   ```

2. **Port not accessible:** Verify port mapping
   ```bash
   docker port ecommerce-postgres
   ```

3. **Firewall blocking:** Check if Windows Firewall is blocking port 5433

4. **Wrong port:** Use `5433` (host port), not `5432` (container port)

