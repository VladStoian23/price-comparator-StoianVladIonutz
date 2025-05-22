package util;

import model.Product;
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
                // Skip empty lines or header
                if (line.trim().isEmpty() || line.startsWith("product_id")) continue;
                String[] fields = line.split(";");
                if (fields.length < 8) continue; // skip malformed lines
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}