# View Layer Implementation Example

This document demonstrates how the new view layer (DTOs) formats data for the frontend, replacing direct entity exposure.

## Before vs After Comparison

### ❌ Before (Direct Entity Exposure)
```java
@GetMapping("/products")
public List<Product> getAllProducts() {
    return productService.getAllProducts();
}
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Cà phê sữa đá",
    "description": "Cà phê pha với sữa đặc, đá viên",
    "price": 25000,
    "stock": 100,
    "categoryId": 1
  }
]
```

### ✅ After (DTO with Structured Response)
```java
@GetMapping("/products")
public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    List<ProductDTO> productDTOs = productMapper.toDTOList(products);
    return ResponseEntity.ok(ApiResponse.success("Products retrieved successfully", productDTOs));
}
```

**Response:**
```json
{
  "success": true,
  "message": "Products retrieved successfully",
  "data": [
    {
      "id": 1,
      "name": "Cà phê sữa đá",
      "description": "Cà phê pha với sữa đặc, đá viên",
      "price": 25000,
      "stock": 100,
      "categoryId": 1,
      "categoryName": "Đồ uống",
      "createdAt": "2024-01-15T10:30:00"
    }
  ],
  "statusCode": 200,
  "timestamp": "2024-01-15T10:30:00"
}
```

## Key Benefits of the View Layer

### 1. **Structured API Responses**
- Consistent response format across all endpoints
- Success/error status indicators
- Descriptive messages
- Timestamps for debugging

### 2. **Data Security**
- Sensitive fields (like passwords) are automatically excluded
- Only necessary data is exposed to frontend
- Control over what information is shared

### 3. **Data Enrichment**
- Add computed fields (like `categoryName` in ProductDTO)
- Include related data without complex joins
- Format dates and numbers appropriately

### 4. **Validation**
- Input validation on DTOs before processing
- Clear error messages for frontend
- Type safety and constraints

### 5. **API Versioning**
- Easy to modify DTOs without changing entities
- Backward compatibility
- Gradual API evolution

## Complete API Response Examples

### Product Endpoints

#### GET /api/products
```json
{
  "success": true,
  "message": "Products retrieved successfully",
  "data": [
    {
      "id": 1,
      "name": "Cà phê sữa đá",
      "description": "Cà phê pha với sữa đặc, đá viên",
      "price": 25000,
      "stock": 100,
      "categoryId": 1,
      "categoryName": "Đồ uống",
      "createdAt": "2024-01-15T10:30:00"
    }
  ],
  "statusCode": 200,
  "timestamp": "2024-01-15T10:30:00"
}
```

#### POST /api/products
```json
{
  "success": true,
  "message": "Resource created successfully",
  "data": {
    "id": 5,
    "name": "Bánh mì pate",
    "description": "Bánh mì với pate thơm ngon",
    "price": 20000,
    "stock": 50,
    "categoryId": 2,
    "categoryName": "Món ăn",
    "createdAt": "2024-01-15T10:35:00"
  },
  "statusCode": 201,
  "timestamp": "2024-01-15T10:35:00"
}
```

#### GET /api/products/{id} (Not Found)
```json
{
  "success": false,
  "message": "Product not found with id: 999",
  "data": null,
  "statusCode": 404,
  "timestamp": "2024-01-15T10:40:00"
}
```

### Category Endpoints

#### GET /api/categories
```json
{
  "success": true,
  "message": "Categories retrieved successfully",
  "data": [
    {
      "id": 1,
      "name": "Đồ uống",
      "description": "Các loại nước giải khát, nước ép, cà phê",
      "productCount": 2,
      "products": null
    },
    {
      "id": 2,
      "name": "Món ăn",
      "description": "Các món ăn chính, món phụ, món tráng miệng",
      "productCount": 2,
      "products": null
    }
  ],
  "statusCode": 200,
  "timestamp": "2024-01-15T10:30:00"
}
```

### User Endpoints

#### GET /api/users (Password Excluded)
```json
{
  "success": true,
  "message": "Users retrieved successfully",
  "data": [
    {
      "id": 1,
      "username": "admin",
      "email": "admin@example.com",
      "fullName": "Quản trị viên",
      "role": "ADMIN",
      "createdAt": "2024-01-15T09:00:00"
    }
  ],
  "statusCode": 200,
  "timestamp": "2024-01-15T10:30:00"
}
```

## Error Response Examples

### Validation Error
```json
{
  "success": false,
  "message": "Validation failed",
  "data": null,
  "statusCode": 400,
  "timestamp": "2024-01-15T10:45:00"
}
```

### Server Error
```json
{
  "success": false,
  "message": "Internal server error",
  "data": null,
  "statusCode": 500,
  "timestamp": "2024-01-15T10:45:00"
}
```

## Frontend Integration

### JavaScript/Fetch Example
```javascript
// Fetch products with proper error handling
async function fetchProducts() {
  try {
    const response = await fetch('/api/products');
    const result = await response.json();
    
    if (result.success) {
      console.log('Products:', result.data);
      displayProducts(result.data);
    } else {
      console.error('Error:', result.message);
      showError(result.message);
    }
  } catch (error) {
    console.error('Network error:', error);
    showError('Network error occurred');
  }
}

// Create product with validation
async function createProduct(productData) {
  try {
    const response = await fetch('/api/products', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(productData)
    });
    
    const result = await response.json();
    
    if (result.success) {
      console.log('Product created:', result.data);
      showSuccess(result.message);
    } else {
      console.error('Error:', result.message);
      showError(result.message);
    }
  } catch (error) {
    console.error('Error:', error);
    showError('Failed to create product');
  }
}
```

## Benefits Summary

1. **Consistency**: All API responses follow the same structure
2. **Security**: Sensitive data is automatically filtered out
3. **Flexibility**: Easy to add new fields or modify existing ones
4. **Validation**: Input validation with clear error messages
5. **Maintainability**: Clear separation between internal entities and external API
6. **Documentation**: Self-documenting API responses
7. **Error Handling**: Standardized error responses across all endpoints

The view layer ensures that your frontend receives clean, structured, and secure data while maintaining the flexibility to evolve your API over time.
