package org.example.pricecomparatorvladstoianiountaccesa_project.repository;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

@Repository
public class ProductRepositoryImplementation implements ProductRepository {
    private final List<Product> products = new ArrayList<>();
    // Map to store user alerts: productId -> targetPrice
    private final Map<String, Double> priceAlerts = new HashMap<>();
    // Map to store triggered alerts: productId -> currentPrice
    private final Map<String, Double> triggeredAlerts = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public Product getProductById(String productId) {
        return products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getProductCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(product.getProductId())) {
                products.set(i, product);
                return;
            }
        }
    }

    @Override
    public void deleteProduct(String productId) {
        products.removeIf(p -> p.getProductId().equals(productId));
    }

    // --- Price Alert Logic for the Custom Price Alert ---

    @Override
    public void setPriceAlert(String productId, double targetPrice) {
        priceAlerts.put(productId, targetPrice);
    }

    @Override
    public void checkAlerts() {
        triggeredAlerts.clear();
        for (Product product : products) {
            Double target = priceAlerts.get(product.getProductId());
            if (target != null && product.getPrice() <= target) {
                triggeredAlerts.put(product.getProductId(), product.getPrice());
            }
        }
    }

    @Override
    public Map<String, Double> getTriggeredAlerts() {
        return new HashMap<>(triggeredAlerts);
    }
}
