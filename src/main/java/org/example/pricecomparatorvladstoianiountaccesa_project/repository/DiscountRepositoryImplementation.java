package org.example.pricecomparatorvladstoianiountaccesa_project.repository;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Discount;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DiscountRepositoryImplementation implements DiscountRepository {
    // Pair each discount with the time it was added
    private static class DiscountEntry {
        Discount discount;
        LocalDateTime addedAt;
        DiscountEntry(Discount discount, LocalDateTime addedAt) {
            this.discount = discount;
            this.addedAt = addedAt;
        }
    }

    private final List<DiscountEntry> discounts = new ArrayList<>();

    @Override
    public void addDiscount(Discount discount) {
        discounts.add(new DiscountEntry(discount, LocalDateTime.now()));
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discounts.stream()
                .map(entry -> entry.discount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Discount> getDiscountsByProduct(String productId) {
        return discounts.stream()
                .filter(entry -> entry.discount.getProduct() != null && entry.discount.getProduct().getProductId().equals(productId))
                .map(entry -> entry.discount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Discount> getDiscountsAddedInLast24Hours() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);
        return discounts.stream()
                .filter(entry -> entry.addedAt.isAfter(cutoff))
                .map(entry -> entry.discount)
                .collect(Collectors.toList());
    }
}
