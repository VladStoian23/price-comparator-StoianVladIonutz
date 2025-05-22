package org.example.pricecomparatorvladstoianiountaccesa_project.model;

public class Store {
    private String storeId;
    private String storeName;
    private String location; //maybe useful when having two of the same franchise in the same city but with different street ADDRESS

    public Store(String storeId, String storeName, String location) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.location = location;
    }
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

  
}
