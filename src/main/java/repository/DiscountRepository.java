package repository;

import model.Discount;

import java.util.ArrayList;
import java.util.List;

public interface DiscountRepository {
    void addDiscount(Discount discount);
    List<Discount> getAllDiscounts();
    List<Discount> getDiscountsByProduct(String productId);
}
