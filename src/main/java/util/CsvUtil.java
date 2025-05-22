package util;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    public static List<Product> readProductsFromCsv(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("product_id")) continue;

                System.out.println("Reading line: " + line);

                String[] fields = line.split(";");
                if (fields.length < 8) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                try {
                    Product product = new Product(
                            fields[0].trim(),
                            fields[1].trim(),
                            fields[2].trim(),
                            fields[3].trim(),
                            Double.parseDouble(fields[4].trim()),
                            fields[5].trim(),
                            Double.parseDouble(fields[6].trim()),
                            fields[7].trim()
                    );
                    products.add(product);
                    System.out.println("Parsed product: " + product);
                } catch (Exception parseEx) {
                    System.out.println("Failed to parse product from line: " + line);
                    parseEx.printStackTrace();
                }
            }
            System.out.println("Total products parsed: " + products.size());
            for (Product p : products) {
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }


}
