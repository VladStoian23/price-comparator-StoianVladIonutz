package org.example.pricecomparatorvladstoianiountaccesa_project.controller;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Product;
import org.springframework.web.bind.annotation.*;
import org.example.pricecomparatorvladstoianiountaccesa_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import util.CsvUtil;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @GetMapping("/load-csv")
    public List<Product> loadProductsFromCsv(@RequestParam String filePath) {
        List<Product> products = CsvUtil.readProductsFromCsv(filePath);
        for (Product product : products) {
            productRepository.addProduct(product);
        }
        productRepository.checkAlerts();
        return products;
    }

    @PostMapping("/upload-csv")
    public List<Product> uploadProductsCsv(@RequestParam("file") MultipartFile file) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("product_id")) continue;
                String[] fields = line.split(";");
                if (fields.length < 8) continue;
                Product product = new Product(
                        fields[0].trim(),
                        fields[1].trim(),
                        fields[2].trim(),
                        fields[3].trim(),
                        Double.parseDouble(fields[4].trim()),
                        fields[5].trim(),
                        Double.parseDouble(fields[6].trim()),
                        fields[7].trim()
                );
                productRepository.addProduct(product);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        productRepository.checkAlerts();
        return products;
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productRepository.getProductsByCategory(category);
    }

    // --- Secton dedicated for Price Alert Endpoints ---

    // Set a target price alert for a product
    @PostMapping("/alerts")
    public String setPriceAlert(@RequestParam String productId, @RequestParam double targetPrice) {
        productRepository.setPriceAlert(productId, targetPrice);
        return "Alert set for product " + productId + " at target price " + targetPrice;
    }

    // Get all triggered alerts (products at or below target price)
    @GetMapping("/alerts/triggered")
    public Map<String, Double> getTriggeredAlerts() {
        return productRepository.getTriggeredAlerts();
    }
}
