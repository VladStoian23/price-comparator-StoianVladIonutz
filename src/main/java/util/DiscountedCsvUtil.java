package util;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Discount;
import org.example.pricecomparatorvladstoianiountaccesa_project.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountedCsvUtil {
    public static List<Discount> readDiscountsFromCsv(String filePath) {
        List<Discount> discounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Skip empty lines or header
                if (line.trim().isEmpty() || line.startsWith("product_id")) continue;
                String[] fields = line.split(";");
                if (fields.length < 9) continue; // skip malformed lines

                // Create Product object
                Product product = new Product(
                        fields[0].trim(), // product_id
                        fields[1].trim(), // product_name
                        fields[5].trim(), // product_category
                        fields[2].trim(), // brand
                        Double.parseDouble(fields[3].trim()), // package_quantity
                        fields[4].trim(), // package_unit
                        0.0, // price (not provided in discount CSV)
                        "" // currency (not provided in discount CSV)
                );

                // Create Discount object
                Discount discount = new Discount(
                        product,
                        Double.parseDouble(fields[8].trim()), // percentage_of_discount
                        LocalDate.parse(fields[6].trim()), // from_date
                        LocalDate.parse(fields[7].trim()) // to_date
                );

                discounts.add(discount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discounts;
    }
}
