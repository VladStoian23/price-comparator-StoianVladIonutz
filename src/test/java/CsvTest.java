import model.Product;
import util.CsvUtil;

import java.util.List;

public class CsvTest {
    public static void main(String[] args) {
        String filePath = "src/main/resources/data/lidl_2025-05-01.csv";
        List<Product> products = CsvUtil.readProductsFromCsv(filePath);
        for (Product product : products) {
            System.out.println(product.getProductId() + " - " + product.getProductName() + " - " + product.getPrice());
        }
    }
}