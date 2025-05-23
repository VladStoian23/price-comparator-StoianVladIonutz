package org.example.pricecomparatorvladstoianiountaccesa_project.repository;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Discount;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.LocalDate;
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

    @Override
    public List<Discount> getBestDiscountsForProducts(List<String> productIds) {
        LocalDate today = LocalDate.now();
        return discounts.stream()
                .map(entry -> entry.discount)
                .filter(discount -> productIds.contains(discount.getProduct().getProductId()))
                .filter(discount -> !today.isBefore(discount.getStartDate()) && !today.isAfter(discount.getEndDate()))
                .collect(Collectors.groupingBy(d -> d.getProduct().getProductId()))
                .values().stream()
                .map(discountList -> discountList.stream()
                        .max((d1, d2) -> Double.compare(d1.getDiscountPercentage(), d2.getDiscountPercentage()))
                        .orElse(null))
                .filter(d -> d != null)
                .collect(Collectors.toList());
    }

    //List products with the highest current percentage discounts across all tracked
    //stores.
    @Override
    public List<Discount> getProductsWithHighestCurrentDiscounts() {
        // For each product, keep only the discount with the highest percentage (no date filter)
        return discounts.stream()
                .map(entry -> entry.discount)
                .collect(Collectors.groupingBy(d -> d.getProduct().getProductId()))
                .values().stream()
                .map(list -> list.stream()
                        .max((d1, d2) -> Double.compare(d1.getDiscountPercentage(), d2.getDiscountPercentage()))
                        .orElse(null))
                .filter(d -> d != null)
                .sorted((d1, d2) -> Double.compare(d2.getDiscountPercentage(), d1.getDiscountPercentage()))
                .collect(Collectors.toList());
    }
}
