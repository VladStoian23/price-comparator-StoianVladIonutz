package org.example.pricecomparatorvladstoianiountaccesa_project.repository;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Discount;
import java.util.List;

public interface DiscountRepository {
    void addDiscount(Discount discount);
    List<Discount> getAllDiscounts();
    List<Discount> getDiscountsByProduct(String productId);
    List<Discount> getDiscountsAddedInLast24Hours(); // NEW
}
