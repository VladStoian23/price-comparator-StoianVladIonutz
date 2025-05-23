package org.example.pricecomparatorvladstoianiountaccesa_project.controller;

import org.example.pricecomparatorvladstoianiountaccesa_project.model.PriceEntry;
import org.example.pricecomparatorvladstoianiountaccesa_project.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceController(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    // Get all price entries (for all products, stores, etc.)
    @GetMapping
    public List<PriceEntry> getAllPriceEntries() {
        return priceRepository.getAllPriceEntries();
    }

    // Get price trend for a specific product
    @GetMapping("/product/{productId}")
    public List<PriceEntry> getPriceEntriesByProduct(@PathVariable String productId) {
        return priceRepository.getPriceEntriesByProduct(productId);
    }

    // Get price trend for a specific store
    @GetMapping("/store/{storeId}")
    public List<PriceEntry> getPriceEntriesByStore(@PathVariable String storeId) {
        return priceRepository.getPriceEntriesByStore(storeId);
    }

    // Get price trend for a specific category
    @GetMapping("/category/{category}")
    public List<PriceEntry> getPriceEntriesByCategory(@PathVariable String category) {
        return priceRepository.getPriceEntriesByCategory(category);
    }

    // Get price trend for a specific brand
    @GetMapping("/brand/{brand}")
    public List<PriceEntry> getPriceEntriesByBrand(@PathVariable String brand) {
        return priceRepository.getPriceEntriesByBrand(brand);
    }

    // Get price trend with combined filters (all params optional)
    @GetMapping("/filter")
    public List<PriceEntry> getPriceEntriesFiltered(
            @RequestParam(required = false) String productId,
            @RequestParam(required = false) String storeId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand
    ) {
        return priceRepository.getPriceEntries(productId, storeId, category, brand);
    }
}
