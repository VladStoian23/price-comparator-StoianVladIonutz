package model;

import java.time.LocalDate;

public class PriceEntry {
    private Product product;
    private Store store;
    private LocalDate date;
    private double price;
    private String currency;

    public PriceEntry(Product product, Store store, LocalDate date, double price, String currency) {
        this.product = product;
        this.store = store;
        this.date = date;
        this.price = price;
        this.currency = currency;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
