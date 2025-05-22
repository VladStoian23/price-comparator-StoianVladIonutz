package org.example.pricecomparatorvladstoianiountaccesa_project.repository;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.PriceEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}