package repository;

import model.Discount;
import model.PriceEntry;

import java.util.List;


public interface PriceRepository {
    void addPriceEntry(PriceEntry priceEntry);
    List<PriceEntry> getAllPriceEntries();
    List<PriceEntry> getPriceEntriesByStore(String storeId);
    List<PriceEntry> getPriceEntriesByProduct(String productId);
}