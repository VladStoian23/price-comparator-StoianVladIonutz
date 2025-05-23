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
                if (fields.length < 9) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                try {
                    String discountStr = fields[8].trim();
                    String fromDateStr = fields[6].trim();
                    String toDateStr = fields[7].trim();

                    if (discountStr.isEmpty() || fromDateStr.isEmpty() || toDateStr.isEmpty()) {
                        System.out.println("Skipping discount with missing fields: " + line);
                        continue;
                    }

                    double discountPercentage = Double.parseDouble(discountStr);
                    if (discountPercentage <= 0) {
                        System.out.println("Skipping discount with zero or negative percentage: " + line);
                        continue;
                    }

                    LocalDate fromDate = LocalDate.parse(fromDateStr);
                    LocalDate toDate = LocalDate.parse(toDateStr);

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
                            discountPercentage,
                            fromDate,
                            toDate
                    );

                    discounts.add(discount);
                } catch (Exception parseEx) {
                    System.out.println("Failed to parse discount from line: " + line);
                    parseEx.printStackTrace();
                }
            }
            System.out.println("Total discounts parsed: " + discounts.size());
            for (Discount d : discounts) {
                System.out.println(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discounts;
    }
}
