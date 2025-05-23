package org.example.pricecomparatorvladstoianiountaccesa_project.repository;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Product;
import java.util.List;
import java.util.Map;

public interface ProductRepository {
    void addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(String productId);
    List<Product> getProductsByCategory(String category);
    void updateProduct(Product product);
    void deleteProduct(String productId);

    // Price alert methods
    void setPriceAlert(String productId, double targetPrice);
    void checkAlerts();
    Map<String, Double> getTriggeredAlerts();
}
