package org.example.pricecomparatorvladstoianiountaccesa_project.repository;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.PriceEntry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class PriceRepositoryImplementation implements PriceRepository {
    private final List<PriceEntry> priceEntries = new ArrayList<>();

    @Override
    public void addPriceEntry(PriceEntry priceEntry) {
        priceEntries.add(priceEntry);
    }

    @Override
    public List<PriceEntry> getAllPriceEntries() {
        return new ArrayList<>(priceEntries);
    }

    @Override
    public List<PriceEntry> getPriceEntriesByStore(String storeId) {
        return priceEntries.stream()
                .filter(entry -> entry.getStore() != null && entry.getStore().getStoreId().equals(storeId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PriceEntry> getPriceEntriesByProduct(String productId) {
        return priceEntries.stream()
                .filter(entry -> entry.getProduct() != null && entry.getProduct().getProductId().equals(productId))
                .collect(Collectors.toList());
    }

    // New: Filter by category
    @Override
    public List<PriceEntry> getPriceEntriesByCategory(String category) {
        return priceEntries.stream()
                .filter(entry -> entry.getProduct() != null && entry.getProduct().getProductCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // New: Filter by brand
    @Override
    public List<PriceEntry> getPriceEntriesByBrand(String brand) {
        return priceEntries.stream()
                .filter(entry -> entry.getProduct() != null && entry.getProduct().getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    // New: Combined filter for trends
    @Override
    public List<PriceEntry> getPriceEntries(String productId, String storeId, String category, String brand) {
        return priceEntries.stream()
                .filter(entry -> productId == null || (entry.getProduct() != null && entry.getProduct().getProductId().equals(productId)))
                .filter(entry -> storeId == null || (entry.getStore() != null && entry.getStore().getStoreId().equals(storeId)))
                .filter(entry -> category == null || (entry.getProduct() != null && entry.getProduct().getProductCategory().equalsIgnoreCase(category)))
                .filter(entry -> brand == null || (entry.getProduct() != null && entry.getProduct().getBrand().equalsIgnoreCase(brand)))
                .collect(Collectors.toList());
    }
}
