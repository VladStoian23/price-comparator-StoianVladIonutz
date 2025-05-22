import org.example.pricecomparatorvladstoianiountaccesa_project.model.Discount;
import org.example.pricecomparatorvladstoianiountaccesa_project.model.Product;
import org.junit.jupiter.api.Test;
import util.DiscountedCsvUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DiscountedCsvTest {

    private boolean isNewDiscount(Discount discount) {
        LocalDate now = LocalDate.now();
        return ChronoUnit.DAYS.between(discount.getStartDate(), now) < 1 &&
               !discount.getStartDate().isAfter(now);
    }

    @Test
    public void testReadDiscountsFromCsv_ValidFile() {
        String filePath = "src/main/resources/data/lidl_discounts_2025-05-08.csv";
        List<Discount> discounts = DiscountedCsvUtil.readDiscountsFromCsv(filePath);

        System.out.println("New Discounts (added in last 24h):");
        for (Discount discount : discounts) {
            if (isNewDiscount(discount)) {
                Product product = discount.getProduct();
                System.out.println(
                    product.getProductId() + " - " +
                    product.getProductName() + " - " +
                    product.getBrand() + " - " +
                    product.getPackageQuantity() + " " +
                    product.getPackageUnit() + " - " +
                    product.getProductCategory() + " - " +
                    discount.getDiscountPercentage() + "% - " +
                    discount.getStartDate() + " to " +
                    discount.getEndDate()
                );
            }
        }
    }
}
