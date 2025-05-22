package org.example.pricecomparatorvladstoianiountaccesa_project.model;

import java.time.LocalDate;

public class Discount {
    private Product product;
    private double discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;

    public Discount(Product product, double discountPercentage, LocalDate startDate, LocalDate endDate) {
        this.product = product;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}