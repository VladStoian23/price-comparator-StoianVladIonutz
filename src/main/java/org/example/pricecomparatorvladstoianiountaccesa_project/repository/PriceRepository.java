package org.example.pricecomparatorvladstoianiountaccesa_project.repository;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.PriceEntry;
import java.util.List;

public interface PriceRepository {
    void addPriceEntry(PriceEntry priceEntry);
    List<PriceEntry> getAllPriceEntries();
    List<PriceEntry> getPriceEntriesByStore(String storeId);
    List<PriceEntry> getPriceEntriesByProduct(String productId);

    // New methods for trend data
    List<PriceEntry> getPriceEntriesByCategory(String category);
    List<PriceEntry> getPriceEntriesByBrand(String brand);
    List<PriceEntry> getPriceEntries(String productId, String storeId, String category, String brand);
}
