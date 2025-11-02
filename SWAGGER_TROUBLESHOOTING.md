# Swagger UI Troubleshooting Guide

## Current Status
403 Forbidden error when accessing Swagger UI at: http://localhost:8081/swagger-ui/index.html

## Configuration Made

### 1. application.properties
```properties
# Swagger/OpenAPI Configuration  
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.enabled=true
```

### 2. SecurityConfig.java
All Swagger paths are explicitly permitted:
- `/swagger-ui/**`
- `/swagger-ui.html`
- `/v3/api-docs/**`
- `/api-docs/**`
- `/swagger-resources/**`
- `/webjars/**`
- `/configuration/**`
- `/favicon.ico`

**IMPORTANT:** Added `.httpBasic(basic -> basic.disable())` and `.formLogin(login -> login.disable())` to prevent default authentication prompts.

### 3. JwtRequestFilter.java
All Swagger paths are excluded from JWT filtering with DEBUG level logging enabled to help diagnose access issues.

### 4. SwaggerConfig.java
Properly configured with Bearer Authentication scheme.

## Logging Enabled
Check the application logs for debug messages like:
```
DEBUG JWT Filter - Path: /swagger-ui/index.html - Will skip: true
```

If you see `Will skip: false`, the path exclusion is not working.

## REQUIRED: Restart Steps

**CRITICAL:** The application MUST be completely restarted for changes to take effect.

1. **Stop the running application completely**
   - If using IDE, click Stop button
   - If using terminal, press Ctrl+C
   - Wait for complete shutdown

2. **Clean Build (Highly Recommended)**
   - Eclipse/IntelliJ: Project → Clean
   - Maven: `mvn clean install`
   - This ensures no cached classes are used

3. **Start the application again**

4. **Wait for startup** - Check logs that Spring Boot started successfully

5. **Access:** http://localhost:8081/swagger-ui/index.html

## Verify Configuration

After restart, check logs for:
1. No errors during startup
2. Spring Security filter chain configured
3. Swagger UI is not being blocked by JWT filter

## Alternative Access URLs

Try these if main URL doesn't work:
1. http://localhost:8081/swagger-ui/index.html (primary)
2. http://localhost:8081/v3/api-docs (should return JSON)
3. http://localhost:8081/api-docs (alternative)

## If Still Not Working

Check application logs for:
- "JWT Filter processing path" vs "skipping path" 
- Any 403 errors in logs
- Spring Security configuration errors
- Swagger bean creation errors

## Common Issues

1. **Old class files cached** → Do clean build
2. **Application not fully restarted** → Complete stop/start cycle
3. **Multiple SecurityConfig classes** → Check for duplicates
4. **Port conflict** → Ensure port 8081 is available

## Next Steps After Restart

Once Swagger is accessible:
1. Test `/api/auth/login` endpoint
2. Copy JWT token from response
3. Click "Authorize" button in Swagger UI
4. Enter: `Bearer <your-token>`
5. Test authenticated endpoints

