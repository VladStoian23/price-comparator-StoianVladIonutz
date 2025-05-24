
# Price Comparator Backend -> Stoian Vlad Ionut

**Contact:** vladstoian23@gmail.com

## Project Overview

This project is a Java Spring Boot backend for a price comparator application. It allows users to compare product prices, track discounts, set price alerts, and analyze price trends across multiple stores.

### Project Structure

- `src/main/java/org/example/pricecomparatorvladstoianiountaccesa_project/`
  - `controller/` - REST controllers for API endpoints (Product, Discount, Price).
  - `model/` - Domain models (Product, Discount, PriceEntry, etc.).
  - `repository/` - In-memory repositories for managing data and business logic.
  - `util/` - Utility classes for CSV parsing and data import.
  - `service/` - First I wanted the projec to run locally without a server. After testing the CRUD methods and filters I moved the app to a new spring app and started using POSTMAN for the api calls.
- `src/main/resources/data/` - Sample and edge-case CSV datasets for products and discounts.

### Build & Run Instructions

**Prerequisites:**  
- Java 17+
- Maven
-POSTMAN for API calls

**To run:**
```sh
mvn spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

### Assumptions & Simplifications

- Data is stored in-memory (no database).
- CSV import is used for initial and updated data.
- Product uniqueness is by `productId`.
- Price alerts are per product, not per store.
- No authentication or user management.
- When we are searching product that have been added recent ("List discounts that have been newly added
 (e.g., within the last 24 hours)" .Im assuming its 24hours since I added in postman the products,not the start_date of the product == today date -1.

### API Usage & Example Requests

#### 1. Load Products from CSV
```http
GET /api/products/load-csv?filePath=src/main/resources/data/edge_cases_2025-05-05.csv
```

#### 2. Load Discounts from CSV
```http
GET /api/discounts/load-csv?filePath=src/main/resources/data/edge_cases_discounts_2025-05-08.csv
```

#### 3. Set a Price Alert
```http
POST /api/products/alerts?productID=P001&targetPrice=9.5
```

#### 4. Check Triggered Alerts
```http
GET /api/products/alerts/triggered
```

#### 5. Get All Products
```http
GET /api/products
```

#### 6. Get All Discounts
```http
GET /api/discounts
```

#### 7. Get Price Trends (filterable)
- All price entries:
  ```http
  GET /api/prices
  ```
- By product:
  ```http
  GET /api/prices/product/P001
  ```
- By store:
  ```http
  GET /api/prices/store/Lidl
  ```
- By category:
  ```http
  GET /api/prices/category/lactate
  ```
- By brand:
  ```http
  GET /api/prices/brand/Zuzu
  ```
- Combined filters:
  ```http
  GET /api/prices/filter?productId=P001&storeId=Lidl&category=lactate&brand=Zuzu
  ```

#### 8. Get Products with Highest Current Discounts
```http
GET /api/discounts/highest-current-discounts
```

#### 9. Optimize Basket for Cost Savings-> not working fully
```http
POST /api/discounts/optimize-basket
Body (JSON): ["P001", "P008", "P020"]
```

### Example CSV Files

- `src/main/resources/data/edge_cases_2025-05-05.csv` - Products with edge cases.
- `src/main/resources/data/edge_cases_discounts_2025-05-05.csv` - Discounts with edge cases.
- `src/main/resources/data/updated_discounts_2025-05-08.csv` - Discounts with edge cases.
- `src/main/resources/data/lidl_discounts_2025-05-08.csv` - Discounts with favorable items.
- `src/main/resources/data/edge_cases_discounts_2025-05-22.csv` -Used for updating a product's price so I can see the alerts working.

**For any issues or questions, contact vladstoian23@gmail.com or use the phone number in the CV**

