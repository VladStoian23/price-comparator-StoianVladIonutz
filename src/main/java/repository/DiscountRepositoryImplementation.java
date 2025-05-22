package repository;

import model.Discount;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountRepositoryImplementation implements DiscountRepository {
    private final List<Discount> discounts = new ArrayList<>();

    @Override
    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return new ArrayList<>(discounts);
    }

    @Override
    public List<Discount> getDiscountsByProduct(String productId) {
        return discounts.stream()
                .filter(d -> d.getProduct() != null && d.getProduct().getProductId().equals(productId))
                .collect(Collectors.toList());
    }
}