package org.example.pricecomparatorvladstoianiountaccesa_project.controller;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Discount;
import org.example.pricecomparatorvladstoianiountaccesa_project.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.DiscountedCsvUtil;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountController(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @GetMapping
    public List<Discount> getAllDiscounts() {
        return discountRepository.getAllDiscounts();
    }

    // Load discounts from a CSV file and store them in the repository (GET with filePath)
    @GetMapping("/load-csv")
    public List<Discount> loadDiscountsFromCsv(@RequestParam String filePath) {
        List<Discount> discounts = DiscountedCsvUtil.readDiscountsFromCsv(filePath);
        for (Discount discount : discounts) {
            discountRepository.addDiscount(discount);
        }
        return discounts;
    }

    // Filter discounts by product ID
    @GetMapping("/product/{productId}")
    public List<Discount> getDiscountsByProduct(@PathVariable String productId) {
        return discountRepository.getDiscountsByProduct(productId);
    }

    // List discounts added in the last 24 hours
    @GetMapping("/recent")
    public List<Discount> getDiscountsAddedInLast24Hours() {
        return discountRepository.getDiscountsAddedInLast24Hours();
    }

    // List products with the highest current percentage discounts across all tracked stores
    @GetMapping("/highest-current-discounts")
    public List<Discount> getProductsWithHighestCurrentDiscounts() {
        return discountRepository.getProductsWithHighestCurrentDiscounts();
    }

    // POST endpoint to optimize shopping list for cost savings
    @PostMapping("/optimize-basket")
    public List<Discount> optimizeBasket(@RequestBody List<String> productIds) {
        return discountRepository.getBestDiscountsForProducts(productIds);
    }

    // Load discounts from an uploaded CSV file (POST with file upload)
    @PostMapping("/upload-csv")
    public List<Discount> uploadDiscountsCsv(@RequestParam("file") MultipartFile file) {
        List<Discount> discounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("product_id")) continue;
                String[] fields = line.split(";");
                if (fields.length < 9) continue;
                // Create Product object
                org.example.pricecomparatorvladstoianiountaccesa_project.model.Product product =
                        new org.example.pricecomparatorvladstoianiountaccesa_project.model.Product(
                                fields[0].trim(),
                                fields[1].trim(),
                                fields[5].trim(),
                                fields[2].trim(),
                                Double.parseDouble(fields[3].trim()),
                                fields[4].trim(),
                                0.0,
                                ""
                        );
                Discount discount = new Discount(
                        product,
                        Double.parseDouble(fields[8].trim()),
                        java.time.LocalDate.parse(fields[6].trim()),
                        java.time.LocalDate.parse(fields[7].trim())
                );
                discountRepository.addDiscount(discount);
                discounts.add(discount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discounts;
    }
}
