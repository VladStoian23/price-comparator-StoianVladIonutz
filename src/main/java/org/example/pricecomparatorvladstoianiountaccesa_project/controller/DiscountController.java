package org.example.pricecomparatorvladstoianiountaccesa_project.controller;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Discount;
import org.example.pricecomparatorvladstoianiountaccesa_project.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.DiscountedCsvUtil;
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

    // Load discounts from a CSV file and store them in the repository
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
}
